<template>
  <div class="content-container" flex justify-around p-5>
    <div class="flex-2">
      <img src='@/assets/content/hospital.png' mb-3 />
      <p>
        常熟理工学院（Changshu Institute of
        Technology），位于江苏省常熟市，是一所江苏省省属全日制普通本科院校，是教育部组织的新一轮本科教学合格评估的首所试点高校、“国家教育体制改革”试点高校、教育部“卓越工程师教育培养计划”试点高校、教育部信息化建设试点高校、“国家级新工科研究与实践项目”入选高校、江苏省首所省市共建试点高校。2017年，学校成为江苏省省级硕士立项建设单位。
        该校前身是创办于1958年的苏州师范专科学校和1984年建校的常熟职业大学，1989年两校合并为常熟高等专科学校。2001年江苏省人口学校并入。2004年5月12日，经教育部批准在常熟高等专科学校基础上建立常熟理工学院。学校现有教职工1280多人，其中专任教师1000多人，高级职称560多人，博士410多人。国务院政府特殊津贴、省有突出贡献中青年专家、省“333工程”、“青蓝工程”、“六大人才高峰”培养人选等160多人，国家级教学名师、省级教学名师4人，省科技创新团队5个，省优秀教学团队4个。
      </p>
    </div>

    <div flex-1 flex justify-around items-center>
      <Icon />
    </div>
  </div>
</template>

<script setup lang='ts'>
import Icon from '@/views/index/icon/Icon.vue'
</script>

<style scoped lang="scss">
p {
  text-indent: 2em;
  line-height: 1.5;
}

.flex-2 {
  flex: 2
}
</style>
