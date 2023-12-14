import { Container, Flex, Link, Skeleton, SkeletonCircle, Text, VStack } from "@chakra-ui/react";
import React from "react";
import ProfileTabs from "./ProfileTabs";
import ProfilePosts from "./ProfilePosts";
import ProfileHeader from "./ProfileHeader";
import useUserProfile from "../../hooks/useUserProfile";
import { useParams,Link as RouterLink } from "react-router-dom";

const ProfilePage = () => {
  const {username}=useParams()
  const {loading,userProfile}=useUserProfile(username)
  if(!loading &&!userProfile)
  return <UserNotFound/>

  return (
    <Container maxW={"container.lg"} py={5}>
      <Flex
        py={10} 
        px={4}
        pl={{ base: 4, md: 10 }}
        w={"full"}
        mx={"auto"}
        flexDirection={"column"}
      >
        {!loading && userProfile && <ProfileHeader />}
        {loading && <ProfileHeaderSkeleton />}
      </Flex>
      <Flex px={{base:2,sm:4}} maxW={"full"} mx={"auto"} borderTop={"1px solid"} borderColor={"whiteAlpha.300"} direction={"column"}>
        <ProfileTabs />
        <ProfilePosts />
      </Flex>
    </Container>
  );
};

export default ProfilePage;

const UserNotFound = () => {
	return (
		<Flex flexDir='column' textAlign={"center"} mx={"auto"}>
			<Text fontSize={"2xl"}>User Not Found</Text>
			<Link as={RouterLink} to={"/"} color={"blue.500"} w={"max-content"} mx={"auto"}>
				Go home
			</Link>
		</Flex>
	);
};

const ProfileHeaderSkeleton = () => {
	return (
		<Flex
			gap={{ base: 4, sm: 10 }}
			py={10}
			direction={{ base: "column", sm: "row" }}
			justifyContent={"center"}
			alignItems={"center"}
		>
			<SkeletonCircle size='24' />

			<VStack alignItems={{ base: "center", sm: "flex-start" }} gap={2} mx={"auto"} flex={1}>
				<Skeleton height='12px' width='150px' />
				<Skeleton height='12px' width='100px' />
			</VStack>
		</Flex>
	);
};
