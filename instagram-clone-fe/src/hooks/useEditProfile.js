import useAuthStore from "../store/useAuthStore"
import useShowToast from "./useShowToast"

const useEditProfile = () => {
  const [isUpdating, setisUpdating] = useState(false)
  const authUser=useAuthStore((state)=>state.user)
  const showToast=useShowToast()
  const editProfile=async(inputs,selectedFile)=>{
    if(isUpdating || !authUser) return;
    setisUpdating(true)
    
  }
}

export default useEditProfile