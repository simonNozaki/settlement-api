# ===============Commands===============
# ... build image ...
# docker build -t dev-settlement-db:1.0.0 .
# ... run container ...
# docker run --name dev-settlement-db -d -p 27017:27017 dev-settlement-db:1.0.0
# ======================================

# MongoDB
FROM mongo:4.0.13-xenial

# DBユーザ
ENV MONGO_INITDB_ROOT_USERNAME=settlement-user
ENV MONGO_INITDB_ROOT_PASSWORD=21405apple
ENV MONGO/INITDB_DATABASE=settlement

# ユーザ認証モードを許可
# RUN mongod --auth

# シードデータの準備
COPY order.json /order.json
COPY billing.json /billing.json
COPY merchant.json /merchant.json

# シードデータの投入
# CMD mongoimport --host=127.0.0.1 --db settlement --collection order --type json --file /order.json --jsonArray
# CMD mongoimport --host=127.0.0.1 --db settlement --collection billing --type json --file /billing.json --jsonArray
# CMD mongoimport --host=127.0.0.1 --db settlement --collection merchant --type json --file /merchant.json --jsonArray

# 待受ポート
EXPOSE 27017
