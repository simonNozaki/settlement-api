# settlement-api
Apache Benchを利用した簡易プロセス確認テスト  
```$shell
$ ab -n 100 -c 100 -p billing.json -T "application/json" http://localhost:8080/v1/order/billing
```

# プロジェクト
取引に対する請求を確定させる決済APIのプロトタイプ.  
## アプリケーションアーキテクチャ
- アプリケーションフレームワーク
  - Spring Boot(2系)
  - Spring WebFlux(5系)
- データストア
  - mongoDB