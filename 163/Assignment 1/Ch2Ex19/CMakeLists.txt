cmake_minimum_required(VERSION 3.2)
project(Ch2Ex19)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp)
add_executable(Ch2Ex19 ${SOURCE_FILES})