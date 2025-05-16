import React, { useEffect, useState } from "react";
import userApi from "../../api/userApi";

const UserData = ({ userId }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setLoading(true);
    setError(null);
    
    userApi.getUserById(userId)
      .then((res) => {
        setUser(res);
        setLoading(false);
      })
      .catch(err => {
        console.error(err);
        setError("Failed to load user data");
        setLoading(false);
      });
  }, [userId]);

  return (
    <div className="user-card bg-white dark:bg-gray-800 rounded-lg shadow-md p-4 max-w-md mx-auto my-4 transition-all duration-300 hover:shadow-lg">
      <div className="flex items-center space-x-4">
        <div className="flex-shrink-0">
          <div className="bg-blue-100 dark:bg-blue-900 text-blue-600 dark:text-blue-300 rounded-full h-12 w-12 flex items-center justify-center font-bold text-lg">
            {loading ? "..." : (user ? user.name.charAt(0).toUpperCase() : "A")}
          </div>
        </div>
        
        <div className="flex-1 min-w-0">
          {loading ? (
            <div className="h-6 bg-gray-200 dark:bg-gray-700 rounded animate-pulse w-3/4"></div>
          ) : error ? (
            <p className="text-red-500 dark:text-red-400 text-sm">{error}</p>
          ) : (
            <>
              <h3 className="font-medium text-gray-900 dark:text-white truncate text-lg">
                {user?.name || "Anonymous User"}
              </h3>
              <p className="text-sm text-gray-500 dark:text-gray-400 truncate">
                {user?.email || "No email provided"}
              </p>
            </>
          )}
        </div>
        
        {user && (
          <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-green-100 dark:bg-green-900 text-green-800 dark:text-green-200">
            Active
          </span>
        )}
      </div>
      
      {user && (
        <div className="mt-4 pt-4 border-t border-gray-200 dark:border-gray-700">
          <div className="flex justify-between text-sm text-gray-600 dark:text-gray-300">
            <span>Joined: {new Date(user.createdAt).toLocaleDateString()}</span>
            <span>ID: {userId}</span>
          </div>
        </div>
      )}
    </div>
  );
};

export default UserData;