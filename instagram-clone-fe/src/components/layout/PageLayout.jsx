import { Box, Flex } from "@chakra-ui/react";
import React from "react";
import Sidebar from "./Sidebar";
import { useLocation } from "react-router-dom";
import useAuthStore from "../../store/authStore";

const PageLayout = ({ children }) => {
  const { pathname } = useLocation();
  const authUser = useAuthStore((state) => state.user);
  const canRenderSidebar = pathname !== "/auth" && authUser;
  return (
    <Flex>
      {canRenderSidebar ? (
        <Box w={{ base: "70px", md: "240px" }}>
          <Sidebar />
        </Box>
      ) : null}
      <Box flex={1} w={{ base: "calc(100%-70px)", md: "calc(100%-240px)" }}>
        {children}
      </Box>
    </Flex>
  );
};

export default PageLayout;
