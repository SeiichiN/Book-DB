# Book-DB
Database of Book

## コンパイル

$ mvn clean package

## デプロイ

Payaraサーバーを起動。

$ <Payara-Directory>/bin/asadmin start-domain

僕は $HOME/Payara_Server を Payara-Directory としている。

Payaraサーバーが起動したら、http://localhost:4848 にアクセス。

管理画面が開くので、左のメニューから Applications を選択。

右側の画面で、Deploy を選択。

Deploy Applications or Modules の画面で、Location: のところで 参照ボタン 
をクリックし、コンパイルで作成した bookDB.war を選択する。

bookDB.war は targetディレクトリにある。

配置した bookDB を Enable とすると、http://localhost:8080/bookDB/ で
アクセスできる。


<!-- 修正時刻: Wed Feb 17 07:13:54 2021 -->
