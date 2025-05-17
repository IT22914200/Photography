import React, { useState, useEffect } from "react";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import CreateUpdatePostModal from "../components/photographyPosts/CreateUpdatePostModal";
import photographyPostApi from "../api/photographyPostApi";
import PostList from "../components/photographyPosts/PostList";

const PhotographyPostsPage = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [postToEdit, setPostToEdit] = useState(null);
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchPosts();
  }, []);

  const fetchPosts = async () => {
    try {
      setLoading(true);
      const data = (await photographyPostApi.getAllPosts()).reverse();
      setPosts(data);
    } catch (error) {
     // toast.error("Failed to fetch posts");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const openCreateModal = () => {
    setPostToEdit(null);
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  const handlePostSubmitSuccess = () => {
    fetchPosts();
    closeModal();
  };

  if (loading) {
    return (
      <div className="flex justify-center items-center h-screen">
        <div className="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-purple-500"></div>
      </div>
    );
  }

  return (
    <div className="container w-[800px] mx-auto px-4 py-8">
      <div className="flex justify-between items-center mb-8">
        <h1 className="text-3xl font-bold text-gray-800"> Posts</h1>
        <button
          onClick={openCreateModal}
          className="px-4 py-2 bg-purple-600 text-white rounded hover:bg-purple-700 transition"
        >
          Create New Post
        </button>
      </div>

      <PostList
        posts={posts ?? []}
        onUpdateOrDelete={fetchPosts}
        onSubmitSuccess={handlePostSubmitSuccess}
      />

      <CreateUpdatePostModal
        isOpen={isModalOpen}
        onClose={closeModal}
        initialPost={postToEdit}
        onSubmitSuccess={handlePostSubmitSuccess}
      />
    </div>
  );
};

export default PhotographyPostsPage;
