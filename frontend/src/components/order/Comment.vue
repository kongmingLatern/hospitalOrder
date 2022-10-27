<template>
  <a-comment v-for="item in 10" w-200 mx-auto my-0>
    <template #actions>
      <span key="comment-basic-like">
        <a-tooltip title="Like">
          <template v-if="action === 'liked'">
            <LikeFilled @click="like" />
          </template>
          <template v-else>
            <LikeOutlined @click="like" />
          </template>
        </a-tooltip>
        <span style="padding-left: 8px; cursor: auto">
          {{ likes }}
        </span>
      </span>
      <span key="comment-basic-dislike">
        <a-tooltip title="Dislike">
          <template v-if="action === 'disliked'">
            <DislikeFilled @click="dislike" />
          </template>
          <template v-else>
            <DislikeOutlined @click="dislike" />
          </template>
        </a-tooltip>
        <span style="padding-left: 8px; cursor: auto">
          {{ dislikes }}
        </span>
      </span>
    </template>
    <template #author>陈楷豪</template>
    <template #avatar>
      <a-avatar src="https://joeschmoe.io/api/v1/random" alt="陈楷豪" />
    </template>
    <template #content>
      <p>
        1、你高超的医术，精湛的手艺，帮助了许多病人起死回生。2、感谢医精湛的手术，让失明的孩子又能重见天日。3、他（她）们为患者带来希望带来光明，他（她）们的事迹感人至深可歌可泣，他（她）们不愧为最美的天使真正的英雄新时代最可爱的人！4、医生对我胜如亲人，能设身处地为患者着想，牺牲自己的休息时间及时为我解除了病痛，令我太感动了。5、妙手回春医术高，医生敬业领风骚。行医治病众人赞，救死扶伤日夜熬。
      </p>
    </template>
    <template #datetime>
      <a-tooltip :title="dayjs().format('YYYY-MM-DD HH:mm:ss')">
        <span>{{ dayjs().fromNow() }}</span>
      </a-tooltip>
    </template>
  </a-comment>
</template>
<script lang="ts" setup>
import dayjs from 'dayjs'
import { LikeFilled, LikeOutlined, DislikeFilled, DislikeOutlined } from '@ant-design/icons-vue'
import { ref } from 'vue'
import relativeTime from 'dayjs/plugin/relativeTime'
dayjs.extend(relativeTime)

const likes = ref<number>(0)
const dislikes = ref<number>(0)
const action = ref<string>()

const like = () => {
  likes.value = 1
  dislikes.value = 0
  action.value = 'liked'
}

const dislike = () => {
  likes.value = 0
  dislikes.value = 1
  action.value = 'disliked'
}
</script>
