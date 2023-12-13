import axios from "axios";
import useShowToast from "./useShowToast";
import useAuthStore from "../store/authStore";
import { useEffect, useState } from "react";

const useLogin = () => {
  const [token, setToken] = useState(localStorage.getItem("token") || null);
  const showToast = useShowToast();
  const loginUser = useAuthStore((state) => state.login);

  const login = async (inputs) => {
    if (!inputs.username || !inputs.password) {
      showToast("Error", "Please fill all the fields", "error");
      return;
    }
    try {
      const response = await axios.post(
        "http://localhost:8888/api/v1/auth/authenticate",
        {
          username: inputs.username,
          password: inputs.password,
        }
      );
      const { token } = response.data;
      setToken(token);
      localStorage.setItem("token", token);
      const user = await axios.get(
        "http://localhost:8888/api/v1/auth/user?token=" + token
      );
      localStorage.setItem("user-info", JSON.stringify(user.data));
      loginUser(user.data);
    } catch (error) {
      showToast("Error", error.response.data.message, "error");
    }
  };
  useEffect(() => {
    const storedToken = localStorage.getItem("token");
    if (storedToken !== token) {
      setToken(storedToken);
    }
  }, [token]);
  return { login };
};

export default useLogin;
