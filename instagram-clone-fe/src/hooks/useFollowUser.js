import React, { useState } from "react";
import useAuthStore from "../store/useAuthStore";

const useFollowUser = (userId) => {
  const [isUpdating, setisUpdating] = useState(false);
  const [isFollowing, setisFollowing] = useState(false);
  const {authUser,setUser} = useAuthStore();
  const handleFollowUser=async()=>{

  }
  useEffect(() => {
    if (authUser) {
      const isFollowing = authUser.following.includes(userId);
      setisFollowing(isFollowing);
    }
  }, [authUser, userId]);
  return {isUpdating,isFollowing,handleFollowUser}
};

export default useFollowUser;
