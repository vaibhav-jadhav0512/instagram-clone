import { Box, Flex, Link, Text, VStack } from "@chakra-ui/react";
import React from "react";
import SuggestedHeader from "./SuggestedHeader";
import SuggestedUser from "./SuggestedUser";

const SuggestedUsers = () => {
  return (
    <VStack py={8} px={6} gap={4}>
      <SuggestedHeader />
      <Flex alignItems={"center"} justifyContent={"space-between"} w={"full"}>
        <Text fontSize={12} fontWeight={"bold"} color={"gray.500"}>
          Suggested for you
        </Text>
        <Text
          fontSize={12}
          fontWeight={"bold"}
          _hover={{ color: "gray.400" }}
          cursor={"pointer"}
        >
          See all
        </Text>
      </Flex>
      <SuggestedUser name="Amar" followers={1213} avatar="https://bit.ly/dan-abramov"/>
      <SuggestedUser name="Akbar" followers={33} avatar="https://bit.ly/ryan-florence"/>
      <SuggestedUser name="Anthony" followers={886} avatar="https://bit.ly/code-beast"/>
      <Box alignSelf={"start"} fontSize={12} color={"gray.500"} mt={5}>
        &copy; 2023 Built by{" "}
        <Link
          href="https://www.linkedin.com/in/vaibhavjadhav0512/"
          target="_blank"
          color={"blue.500"}
          fontSize={14}
        >
          Vaibhav
        </Link>
      </Box>
    </VStack>
  );
};

export default SuggestedUsers;
