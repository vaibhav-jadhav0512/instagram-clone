import { Avatar, Button, Flex, Text } from "@chakra-ui/react";
import React from "react";
import useSignUpWithEmailAndPassword from "../../hooks/useSignUpWithEmailAndPassword";
import useAuthStore from "../../store/useAuthStore";
import { Link } from "react-router-dom";

const SuggestedHeader = () => {
  const { logout, loading } = useSignUpWithEmailAndPassword();
  const authUser = useAuthStore((state) => state.user);
  return (
    <Flex justifyContent={"space-between"} alignItems={"center"} w={"full"}>
      <Flex alignItems={"center"} gap={2}>
        <Link to={authUser.username}>
          <Avatar
            name={authUser.username}
            size={"lg"}
            src={authUser.profilePicSrc}
          />
        </Link>
        <Link to={authUser.username}>
          <Text fontSize={12} fontWeight={"bold"}>
            {authUser.username}
          </Text>
        </Link>
      </Flex>
      <Button
        fontSize={14}
        fontWeight={"medium"}
        color={"blue.400"}
        cursor={"pointer"}
        size={"xs"}
        background={"transparent"}
        _hover={{ background: "transparent" }}
        onClick={logout}
        isLoading={loading}
      >
        Logout
      </Button>
    </Flex>
  );
};

export default SuggestedHeader;
