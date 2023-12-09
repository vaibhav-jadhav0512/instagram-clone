import {
  Box,
  Container,
  Flex,
  Skeleton,
  SkeletonCircle,
  VStack,
} from "@chakra-ui/react";
import React, { useEffect, useState } from "react";
import FeedPost from "./FeedPost";

const FeedPosts = () => {
  const [isLoading, setisLoading] = useState(true);
  useEffect(() => {
    setTimeout(() => {
      setisLoading(false);
    }, 2000);
  }, []);

  return (
    <Container maxW={"container.sm"} py={10} px={2}>
      {isLoading &&
        [1, 2, 3, 4].map((item, idx) => (
          <VStack key={idx} gap={4} alignItems={"flex-start"} mb={10}>
            <Flex gap={2}>
              <SkeletonCircle size="10" />
              <VStack gap={2} alignItems={"flex-start"}>
                <Skeleton height={"10px"} w={"200px"} />
                <Skeleton height={"10px"} w={"200px"} />
              </VStack>
            </Flex>
            <Skeleton w={"full"}>
              <Box h={"500px"}> Contents wrapped</Box>
            </Skeleton>
          </VStack>
        ))}
      {!isLoading && (
        <>
          <FeedPost
            img="/images/img1.png"
            username="vaibhav"
            avatar="/images/img1.png"
          />
          <FeedPost
            img="/images/img2.png"
            username="karan"
            avatar="/images/img2.png"
          />
          <FeedPost
            img="/images/img3.png"
            username="arjun"
            avatar="/images/img3.png"
          />
          <FeedPost
            img="/images/img4.png"
            username="saurabh"
            avatar="/images/img4.png"
          />
        </>
      )}
    </Container>
  );
};

export default FeedPosts;
