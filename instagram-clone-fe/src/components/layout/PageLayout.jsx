import { Box, Flex } from "@chakra-ui/react";
import React from "react";
import Sidebar from "./Sidebar";
import { useLocation } from "react-router-dom";
import useAuthStore from "../../store/useAuthStore";
import Navbar from "./Navbar";

const PageLayout = ({ children }) => {
  const { pathname } = useLocation();
  const authUser = useAuthStore((state) => state.user);
  const canRenderSidebar = pathname !== "/auth" && authUser;
  const canRenderNavbar = !authUser && pathname !== "/auth";
  return (
    <Flex flexDir={canRenderNavbar ? "column" : "row"}>
      {canRenderSidebar ? (
        <Box w={{ base: "70px", md: "240px" }}>
          <Sidebar />
        </Box>
      ) : null}
      {canRenderNavbar ? <Navbar /> : null}
      <Box flex={1} w={{ base: "calc(100%-70px)", md: "calc(100%-240px)" }} >
        {children}
      </Box>
    </Flex>
  );
};

export default PageLayout;
