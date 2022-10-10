<template>
  <div>
    <a-calendar v-model:value="value" :fullscreen="false" @panelChange="onPanelChange" v-show="visible"
      :style="{ width: '300px', border: '1px solid #d9d9d9', borderRadius: '4px' }" />
    <a-button type="primary" @click="changeStatus">
      {{ visible ? '隐藏日期' : '显示日期' }}
    </a-button>
  </div>
</template>
<script lang="ts" setup>
import { ref } from 'vue';
import type { Dayjs } from 'dayjs';

const value = ref<Dayjs>();
const visible = ref<Boolean>(true)
const onPanelChange = (value: Dayjs, mode: string) => {
  console.log(value, mode);
};
const changeStatus = () => {
  visible.value = !visible.value
}

</script>

