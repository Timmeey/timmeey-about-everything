############# SETUP DEPENDENCIES #############
FROM jekyll/builder:latest AS builder
ENV GAIA_HOME=/usr/local/gaia/
RUN mkdir -p $GAIA_HOME
WORKDIR $GAIA_HOME
# add source
ADD ./Gemfile $GAIA_HOME
RUN chmod -R  777  ./
RUN ["/bin/bash","bundle","install"]

############# BUILDER PART #############
ADD . $GAIA_HOME

RUN ["/bin/bash","jekyll","build","-s",".","-d","./_site"]

############# ARTIFACT PART #############
FROM nginx:latest
COPY  --from=builder /usr/local/gaia/_site/ /usr/share/nginx/html/
