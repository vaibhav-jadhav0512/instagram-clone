import React, { useEffect, useState } from "react";
import useAuthStore from "../store/useAuthStore";
import useUserProfile from "./useUserProfile";
import { useToast } from "@chakra-ui/react";
import axios from "axios";
import { useParams } from "react-router-dom";
import useShowToast from "./useShowToast";

const useFollowUser = (userId) => {
  const [isUpdating, setisUpdating] = useState(false);
  const [isFollowing, setisFollowing] = useState(false);
  const [token, setToken] = useState(localStorage.getItem("token") || null);
  const authUser = useAuthStore((state)=>state.user);
  const setUser = useAuthStore((state)=>state.setUser);
  const { username } = useParams();
  const { userProfile, setUserProfile } = useUserProfile(username);
  const showToast = useShowToast();
  const handleFollowUser = async () => {
    setisUpdating(true);
    try {
      const response = await axios.post(
        "http://localhost:8888/api/v1/follow/request",
        { followerUserId: authUser.userId, followingUserId: userId },{
          headers: { Authorization: `Bearer ${token}` }
      }
      );
      showToast("Success", "Followed successfully", "success");
      if (isFollowing) {
        setUser({
          ...authUser,
          following: authUser?.following?.filter((uid) => uid !== userId),
        });
        setUserProfile({
          ...userProfile,
          followers: userProfile?.followers?.filter(
            (uid) => uid !== authUser.userId
          ),
        });
        localStorage.setItem(
          "user-info",
          JSON.stringify({
            ...authUser,
            following: authUser.following.filter((uid) => uid !== userId),
          })
        );
        setisFollowing(false);
      } else {
        setUser({ ...authUser, following: [...authUser.following, userId] });
        setUserProfile({
          ...authUser,
          followers: [...userProfile.followers, authUser.userId],
        });
        localStorage.setItem(
          "user-info",
          JSON.stringify({
            ...authUser,
            following: [...authUser.following, userId],
          })
        );
        setisFollowing(true);
      }
    } catch (error) {
      showToast("Error", error.message, "error");
    } finally {
      setisUpdating(false);
    }
  };
  useEffect(() => {
    if (authUser) {
      const isFollowing = authUser?.following?.includes(userId);
      setisFollowing(isFollowing);
    }
  }, [authUser, userId]);
  return { isUpdating, isFollowing, handleFollowUser };
};

export default useFollowUser;
