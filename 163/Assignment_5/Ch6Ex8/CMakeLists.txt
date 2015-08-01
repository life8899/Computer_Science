cmake_minimum_required(VERSION 3.2)
project(Ch6Ex8)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp)
add_executable(Ch6Ex8 ${SOURCE_FILES})