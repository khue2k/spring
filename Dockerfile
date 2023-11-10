FROM ubuntu:latest
LABEL authors="songk"

ENTRYPOINT ["top", "-b"]