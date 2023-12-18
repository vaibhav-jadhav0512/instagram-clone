import axios from "axios";
import useAuthStore from "../store/useAuthStore";
import userProfileStore from "../store/userProfileStore";
import useShowToast from "./useShowToast";
import { filter } from "@chakra-ui/react";
import { useState } from "react";

const useEditProfile = () => {
  const [isUpdating, setisUpdating] = useState(false);
  const [token, setToken] = useState(localStorage.getItem("token") || null);
  const authUser = useAuthStore((state) => state.user);
  const setAuthUser = useAuthStore((state) => state.setUser);
  const setUserProfile = userProfileStore((state) => state.setUserProfile);
  const showToast = useShowToast();
  const editProfile = async (inputs, selectedFile) => {
    if (isUpdating || !authUser) return;
    setisUpdating(true);
    if (selectedFile) {
      const formData = new FormData();
      formData.append("file", selectedFile);
      try {
        const response = await axios.post(
          "http://localhost:8888/api/v1/image/upload",
          formData,
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "multipart/form-data",
            },
          }
        );
        showToast("Success", response.data, "success");
      } catch (error) {
        showToast("Error", error.message, "error");
      } finally {
        setisUpdating(false);
      }
    }
    if (inputs.fullname.length !== 0 || inputs.bio.length !== 0) {
      const updatedUser = {
        ...authUser,
        fullname: inputs.fullname || authUser.fullname,
        bio: inputs.bio || authUser.bio,
      };
      try {
        const response = await axios.put(
          "http://localhost:8888/user/profile/update",
          updatedUser,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        localStorage.setItem("user-info", JSON.stringify(updatedUser));
        setAuthUser(updatedUser);
        setUserProfile(updatedUser);
        showToast("Success", "User profile updated successfully!", "success");
      } catch (error) {
        showToast("Error", error.message, "error");
      }
    }
  };
  return { isUpdating, editProfile };
};

export default useEditProfile;
