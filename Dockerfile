FROM nginx:alpine

ENV GAIA_HOME=/usr/local/gaia/
RUN mkdir -p $GAIA_HOME
WORKDIR $GAIA_HOME
ADD . $GAIA_HOME


############# ARTIFACT PART #############
COPY  --from=builder /usr/local/gaia/_site/ /usr/share/nginx/html/
