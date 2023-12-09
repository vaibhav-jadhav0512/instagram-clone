import { Box, Container, Flex, Image, VStack } from "@chakra-ui/react";
import React from "react";
import AuthForm from "../auth/AuthForm"

const AuthPage = () => {
  return (
    <Flex minH={"100vh"} justifyContent={"center"} alignItems={"center"} px={4}>
      <Container maxW={"container.md"} padding={0}>
      <Flex justifyContent={"center"} alignItems={"center"} gap={10}>
        <Box display={{ base: "none", md: "block" }}>
          <Image src="/images/auth.png" h={650} alt={"Phone img"} />
        </Box>
        <VStack spacing={4} align={"stretch"}>
          <AuthForm />
          <Box textAlign={"center"}>Get the app.</Box>
          <Flex gap={5} justifyContent={"center"}>
            <Image src="/images/playstore.png" h={10} alt={"Playstore img"} />
            <Image src="/images/microsoft.png" h={10} alt={"Microsoft img"} />
          </Flex>
        </VStack>
        </Flex>
      </Container>
    </Flex>
  );
};

export default AuthPage;
