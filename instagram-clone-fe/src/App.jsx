import { Navigate, Route, Routes } from "react-router-dom";
import HomePage from "./components/pages/HomePage";
import AuthPage from "./components/pages/AuthPage";
import PageLayout from "./components/layout/PageLayout";
import ProfilePage from "./components/profile/ProfilePage";
import useAuthStore from "./store/useAuthStore";

function App() {
  const authUser=useAuthStore(state=>state.user)
  return (
    <PageLayout>
      <Routes>
        <Route path="/" element={authUser?<HomePage />:<Navigate to="/auth"/>} />
        <Route path="/auth" element={!authUser?<AuthPage />:<Navigate to="/"/>} />
        <Route path="/:username" element={<ProfilePage />} />
      </Routes>
    </PageLayout>
  );
}

export default App;
