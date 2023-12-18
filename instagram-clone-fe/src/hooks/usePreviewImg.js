import { useState } from "react";
import useShowToast from "./useShowToast";

const usePreviewImg = () => {
  const [selectedFile, setselectedFile] = useState();
  const [selectedFileUrl, setselectedFileUrl] = useState();
  const showToast = useShowToast();
  const maxFileSize = 2 * 1024 * 1024;
  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file || file.type.startsWith("image/")) {
      if (file.size > maxFileSize) {
        showToast("Error", "File size must be less than 2MB", "error");
        setselectedFile(null);
        return;
      }
      setselectedFile(file)
      const reader = new FileReader();
      reader.onloadend = () => {
        setselectedFileUrl(reader.result);
      };
      reader.readAsDataURL(file);
    } else {
      showToast("Error", "Please select an image", "error");
      setselectedFile(null);
    }
  };
  return {selectedFile,handleImageChange,setselectedFile,selectedFileUrl}
};

export default usePreviewImg;
