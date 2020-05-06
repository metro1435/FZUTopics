module.exports = {
    // css 相关选项
    css: {
        /*为预处理器 loader 传递自定义选项*/
        loaderOptions: {
            sass: {
                data: `@import "@/public/theme.css";`
            }
        }
    },
}