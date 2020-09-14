package org.devops

def Email(status, emailUser) {
    emailext body:"""
     <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
        </head>

        <body>
            <table width="95%" cellpadding="0" style="font-size: 11px;">
                <tr>
                    <td><b style="color: #0b610b;">构建信息</b></td>
                </tr>
                <tr>
                    <td>
                        <ul>
                            <li>项目名称:${JOB_NAME}</li>
                            <li>构建编号:${BUILD_ID}</li>
                            <li>构建状态:${status}</li>
                            <li>项目地址:<a href="${BUILD_URL}">${BUILD_URL}</a></li>
                            <li>构建日志:<a href="${BUILD_URL}console">${BUILD_URL}console</a></li>
                        </ul>
                    </td>
                </tr>
            </table>
        </body>

        </html>
    """,
    subject: "Jenkins-${JOB_NAME}项目构建信息",
    to: emailUser
}
