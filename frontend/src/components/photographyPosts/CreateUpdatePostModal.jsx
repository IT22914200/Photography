import React, { useState, useRef, useEffect } from 'react';
import Modal from 'react-modal';
import { toast } from 'react-toastify';
import cookingPostApi from '../../api/photographyPostApi';
import mediaApi from '../../api/mediaApi';
import { uploadFile } from '../../services/uploadFileService';

// Make sure to bind modal to your app element for accessibility
Modal.setAppElement('#root');

const CreateUpdatePostModal = ({ isOpen, onClose, initialPost = null,onSubmitSuccess }) => {
  const [isLoading, setIsLoading] = useState(false);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [mediaFiles, setMediaFiles] = useState([]);
  const [mediaPreview, setMediaPreview] = useState([]);
  const [uploadProgress, setUploadProgress] = useState({});
  const fileInputRef = useRef(null);
  const userId = localStorage.getItem("userId")
  
  // Set up initial values if editing
  useEffect(() => {
    if (initialPost) {
      setTitle(initialPost.title || '');
      setDescription(initialPost.description || '');
      // Media previews would need to be fetched from the server
      if (initialPost.media && initialPost.media.length > 0) {
        const previews = initialPost.media.map(item => ({
          id: item.id,
          url: item.url,
          type: item.type,
          existingMedia: true
        }));
        setMediaPreview(previews);
      }
    } else {
      // Reset form when opening for a new post
      resetForm();
    }
  }, [initialPost, isOpen]);

  const handleFileSelect = (e) => {
    const files = Array.from(e.target.files);
    
    // Validate file types
    const invalidFiles = files.filter(file => 
      !file.type.startsWith('image/') && !file.type.startsWith('video/')
    );
    
    if (invalidFiles.length > 0) {
      toast.error('Only image and video files are allowed');
      return;
    }
    
    // Validate media count - maximum 3 media items
    const totalMedia = mediaFiles.length + files.length;
    if (totalMedia > 3) {
      toast.error('Maximum 3 media files allowed');
      return;
    }
    
    // Check for video count
    const existingVideos = mediaFiles.filter(file => file.type.startsWith('video/'));
    const newVideos = files.filter(file => file.type.startsWith('video/'));
    
    if (existingVideos.length + newVideos.length > 1) {
      toast.error('Only one video is allowed');
      return;
    }
    
    // Validate video duration
    const validateVideoDuration = async (videoFile) => {
      return new Promise((resolve, reject) => {
        const video = document.createElement('video');
        video.preload = 'metadata';
        video.onloadedmetadata = function() {
          window.URL.revokeObjectURL(video.src);
          if (video.duration > 30) {
            reject('Video must not exceed 30 seconds');
          } else {
            resolve(true);
          }
        };
        video.src = URL.createObjectURL(videoFile);
      });
    };
    
    // Process video files for duration validation
    const processFiles = async () => {
      try {
        for (const file of newVideos) {
          await validateVideoDuration(file);
        }
        
        // All validations passed, update state
        setMediaFiles(prev => [...prev, ...files]);
        
        // Create preview URLs
        const newPreviews = files.map(file => ({
          url: URL.createObjectURL(file),
          type: file.type.startsWith('image/') ? 'image' : 'video',
          file: file
        }));
        
        setMediaPreview(prev => [...prev, ...newPreviews]);
        
      } catch (error) {
        toast.error(error);
      }
    };
    
    if (newVideos.length > 0) {
      processFiles();
    } else {
      // No videos to validate, just update state
      setMediaFiles(prev => [...prev, ...files]);
      
      // Create preview URLs
      const newPreviews = files.map(file => ({
        url: URL.createObjectURL(file),
        type: file.type.startsWith('image/') ? 'image' : 'video',
        file: file
      }));
      
      setMediaPreview(prev => [...prev, ...newPreviews]);
    }
  };

  const removeMedia = (index) => {
    // Remove from previews
    setMediaPreview(prev => prev.filter((_, i) => i !== index));
    
    // Remove from files if it's a new file
    if (!mediaPreview[index].existingMedia) {
      setMediaFiles(prev => prev.filter((_, i) => {
        // Match the file with the preview
        const previewsBeforeIndex = mediaPreview.slice(0, index)
          .filter(p => !p.existingMedia).length;
        return i !== (index - (mediaPreview.length - mediaFiles.length - previewsBeforeIndex));
      }));
      
      // Revoke object URL to prevent memory leaks
      URL.revokeObjectURL(mediaPreview[index].url);
    }
  };

  const validateForm = () => {
    if (!title.trim()) {
      toast.error('Title is required');
      return false;
    }
    
    if (!description.trim()) {
      toast.error('Description is required');
      return false;
    }
    
    // At least one media item is required
    if (mediaPreview.length === 0) {
      toast.error('At least one photo or video is required');
      return false;
    }
    
    return true;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!validateForm()) return;
    
    setIsLoading(true);
    
    try {
      let postData = {
        title,
        description,
        createdAt: new Date(),
        likeCount: initialPost?.likeCount || 0,
        deleteStatus: false,
        createdBy : userId,
      };
      
      let postId;
      
      // Create or update the post first
      if (initialPost) {
        // Update existing post
        const updatedPost = await cookingPostApi.updatePost(initialPost.id, postData);
        postId = updatedPost.id;
        
        // Handle removed media items
        if (initialPost.media) {
          const existingMediaIds = mediaPreview
            .filter(item => item.existingMedia)
            .map(item => item.id);
          
          const mediaToDelete = initialPost.media
            .filter(item => !existingMediaIds.includes(item.id))
            .map(item => item.id);
          
          // Delete media items that were removed
          for (const mediaId of mediaToDelete) {
            await mediaApi.deleteMedia(mediaId);
          }
        }
      } else {
        
      