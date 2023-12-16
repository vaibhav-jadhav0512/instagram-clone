import { useEffect, useState } from "react";
import useShowToast from "./useShowToast";
import axios from "axios";
import userProfileStore from "../store/userProfileStore";

const useUserProfile = (username) => {
  const [loading, setloading] = useState(true);
  const showToast = useShowToast();
  const { userProfile, setUserProfile } = userProfileStore();
  useEffect(() => {
    const getUserProfile = async () => {
      setloading(true);
      try {
        const user = await axios.get(
          "http://localhost:8888/user/get/profile/" + username
        );
        setUserProfile(user.data);
      } catch (error) {
        setUserProfile(null);
        showToast("Error", error.message, "error");
      } finally {
        setloading(false);
      }
    };
    getUserProfile()
    // eslint-disable-next-line
  }, [setUserProfile,username]);
  return { loading, userProfile };
};

export default useUserProfile;
