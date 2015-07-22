<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">

<head>
  <link rel="stylesheet" th:href="@{/resources/css/bootstrap.min.css}" />

</head>
<body>
	<div class="container" th:fragment="content">



		<table class="box-table-a">
			<caption>thyMeleaf使用教程</caption>
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">这是标题1</th>
					<th scope="col">这是标题2</th>
					<th scope="col">这是标题3</th>

				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>朱遇平</td>
					<td>java</td>
					<td>程序猿</td>

				</tr>

				<tr>
					<td>2</td>
					<td>张三</td>
					<td>php</td>
					<td>程序猿</td>

				</tr>
				<tr>
					<td>3</td>
					<td>李四</td>
					<td>c++</td>
					<td>程序猿</td>

				</tr>

			</tbody>
		</table>


	</div>
</body>
</html>