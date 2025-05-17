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
        
        
    