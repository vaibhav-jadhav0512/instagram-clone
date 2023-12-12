import { useState, useEffect } from "react";
import axios from "axios";
import useShowToast from "./useShowToast";

const useSignUpWithEmailAndPassword = () => {
  const [token, setToken] = useState(localStorage.getItem("token") || null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const showToast = useShowToast();

  const signup = async (inputs) => {
    if (
      !inputs.fullName ||
      !inputs.userName ||
      !inputs.email ||
      !inputs.password
    ) {
      showToast("Error", "Please fill all the fields", "error");
      return;
    }

    try {
      setLoading(true);
      const response = await axios.post(
        "http://localhost:8888/api/v1/auth/register",
        {
          fullname: inputs.fullName,
          username: inputs.userName,
          email: inputs.email,
          password: inputs.password,
        }
      );
      const { token } = response.data;
      setToken(token);
      localStorage.setItem("token", token);
      setError(null);
    } catch (error) {
        console.log(error)
      showToast("Error", error.message, "error");
      setError("Something went wrong!");
      setToken(null);
    } finally {
      setLoading(false);
    }
  };

  const logout = () => {
    setToken(null);
    localStorage.removeItem("token");
    setError(null);
  };

  const isAuthenticated = () => {
    return token !== null;
  };

  useEffect(() => {
    const storedToken = localStorage.getItem("token");
    if (storedToken !== token) {
      setToken(storedToken);
    }
  }, [token]);

  return {
    token,
    error,
    loading,
    signup,
    logout,
    isAuthenticated,
  };
};

export default useSignUpWithEmailAndPassword;
