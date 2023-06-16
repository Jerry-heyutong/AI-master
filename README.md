# AI-Master - GPTCosplay

`GPTCosplay` 是一个玩具聊天应用，使用 OpenAI's GPT 进行对话和角色扮演，可以通过以下链接访问

```
https://s571298n46.zicp.fun/
```

## 特性

- 支持对话和角色扮演
- 通过输入API key和选择角色后启动对话
- 可以选择预设角色，或者自定义角色
- 支持保存API Key和角色设定，避免重复设置
- 支持在本地使用

## 如何部署

1. 将代码下载到本地计算机
2. 安装java8
3. 安装maven3
4. mvn install
5. java -jar -Dspring.profiles.active= release api-0.0.1-SNAPSHOT.jar
6. 访问localhost:8899
7. 注册 OpenAI API 并获取 API Key
8. 将API Key粘贴到“apiKey-input”输入框
9. 填入角色设定的prompt
10. 单击“保存”按钮保存API Key和角色设定
11. 输入想说的话
12. 单击“发送”按钮发送消息
ps: 需要自行部署代理,在application-XX.yml中配置你的代理ip和端口号。 XX表示环境参数，枚举: dev、 release、 test。
## 技术依赖项

- Bootstrap：提供了易于使用的CSS和JavaScript库
- jQuery：简化了JavaScript代码的编写
- highlight.js：用于代码高亮显示
- OpenAI's GPT：提供了对话和角色扮演的API
- spring-boot-webflux
- netty

## 作者

若有向开发者提出问题或建议，请使用以下联系方式进行联系：
- E-mail: 294552613@qq.com

## 许可证

完全开源。商用请标明出处~