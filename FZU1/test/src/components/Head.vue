<template>
    <header class="head">
            <div class="header-inner">
                <h1 class="title-top">
                    <router-link to="/Home" class="logo">福大热话</router-link>
                </h1>
                <nav class="nav-top">
                    <ul id="menu-top" class="nav-top-item">
                        <li class="menu-item">
                             <router-link to="/Course">课程查询</router-link>
                        </li>
                        <li class="menu-item">
                            <router-link to="/News">实事新闻</router-link>
                        </li>
                        <li class="menu-item">
                            <router-link to="/Home">收藏夹</router-link>
                        </li>
                    </ul>
                </nav>
            </div>
    </header>
</template>

<script>
    export default {
        name:"Head",
    }
</script>

<style scoped src='../style/head.css'>
</style>