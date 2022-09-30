<template>
  <a-table :columns="columns" :data-source="data" :pagination="false">
    <template #headerCell="{ column }">
      <template v-if="column.key === 'time'">
        <span>
          Time
        </span>
      </template>
    </template>

    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'name'">
        <a>
          {{ record.name }}
        </a>
      </template>
      <template v-else-if="column.key === 'action'">
        <span>
          <a>预约</a>
        </span>
      </template>
    </template>
  </a-table>
</template>
<script lang="ts" setup>
const columns = [
  {
    name: '预约时间',
    dataIndex: 'time',
    key: 'time',
  },
  {
    title: 'Action',
    key: 'action',
  },
];

const data = [
  {
    key: '1',
    time: '8:00 - 12:00',
  },
  {
    key: '2',
    time: '13:00 - 18:00',
  }
];

</script>


<style lang='scss' scoped>
:deep(.ant-table-cell) {
  padding: 5px;
  text-align: center;
}
</style>