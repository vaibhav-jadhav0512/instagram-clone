import { Route, Routes } from "react-router-dom";
import HomePage from "./components/pages/HomePage";
import AuthPage from "./components/pages/AuthPage";
import PageLayout from "./components/layout/PageLayout";
import ProfilePage from "./components/profile/ProfilePage";

function App() {
  return (
    <PageLayout>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/auth" element={<AuthPage />} />
        <Route path="/:username" element={<ProfilePage />} />
      </Routes>
    </PageLayout>
  );
}

export default App;
