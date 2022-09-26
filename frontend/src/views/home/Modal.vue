<template>
  <a-modal v-model:visible="visible" title="Basic Modal" @ok="handleOk">
    <p>Some contents...</p>
    <p>Some contents...</p>
    <p>Some contents...</p>
  </a-modal>
</template>
<script lang="ts" setup>
import { ref } from 'vue';
const props = defineProps<{
  visible: Boolean;
}>()
const visible = ref<Boolean>(props.visible)
console.log(visible);
const handleOk = (e: MouseEvent) => {
  console.log(e);
  visible.value = false;
};
</script>

