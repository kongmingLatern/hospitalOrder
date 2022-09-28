<template>
  <a-list item-layout="horizontal" :data-source="data">
    <template #renderItem="{ item, index }">
      <a-list-item>
        <a-list-item-meta :description="item.description">
          <template #title>
            <span>{{index + 1}}.</span>
            <a href="https://www.antdv.com/">{{ item.title }}</a>
          </template>
        </a-list-item-meta>
      </a-list-item>
    </template>
  </a-list>
</template>
<script lang="ts" setup>
interface DataItem {
  description: string;
  title: string;
}
const data: DataItem[] = [
  {
    description: '中国共产党常熟理工学院第四次党员代表大会胜利闭幕',
    title: '新时代呼唤新使命，新使命开启新征程。',
  },
  {
    description: '关于开展“不负青春志 奋斗正当时”毕业生就创业风采展的通知',
    title: '学校召开第四次党代会各代表团第二次分团会议',
  }
];
</script>

<style lang='scss'>
.ant-card-body {
  padding: unset;
  padding: 10px 0 10px 10px;
}

.ant-list-item-meta-title {
  overflow: hidden; //超出的文本隐藏
  text-overflow: ellipsis; //溢出用省略号显示
  display: -webkit-box;
  -webkit-line-clamp: 1; // 超出多少行
  -webkit-box-orient: vertical;
}

.ant-list-item-meta-description {
  font-size: 12px;
  overflow: hidden; //超出的文本隐藏
  text-overflow: ellipsis; //溢出用省略号显示
  display: -webkit-box;
  -webkit-line-clamp: 1; // 超出多少行
  -webkit-box-orient: vertical;
}
</style>