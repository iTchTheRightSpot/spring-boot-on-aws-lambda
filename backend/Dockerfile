FROM public.ecr.aws/lambda/provided:al2
COPY target/server /var/task/server
ENTRYPOINT ["/var/task/server"]