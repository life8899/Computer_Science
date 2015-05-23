cmake_minimum_required(VERSION 3.2)
project(Ch5Ex13)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp)
add_executable(Ch5Ex13 ${SOURCE_FILES})