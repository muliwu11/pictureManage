<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import { PIC_REVIEW_STATUS_OPTIONS } from '../constants/picture.ts'
import dayjs from 'dayjs'
import { listPictureTagCategoryUsingGet } from '@/api/pictureController.ts'
import { message } from 'ant-design-vue'


interface Props {
  onSearch?: (searchParams: API.PictureQueryRequest) => void
}

const props = defineProps<Props>()

// 搜索条件
const searchParams = reactive<API.PictureQueryRequest>({})

// 获取数据
const doSearch = () => {
  props.onSearch?.(searchParams)
}

const dateRange = ref<[]>([])

/**
 * 日期范围更改时触发
 * @param dates
 * @param dateStrings
 */
const onRangeChange = (dates: any[], dateStrings: string[]) => {
  if (dates.length < 2) {
    searchParams.startEditTime = undefined
    searchParams.endEditTime = undefined
  } else {
    searchParams.startEditTime = dates[0].toDate()
    searchParams.endEditTime = dates[1].toDate()
  }
}

const rangePresets = ref([
  { label: '过去 7 天', value: [dayjs().add(-7, 'd'), dayjs()] },
  { label: '过去 14 天', value: [dayjs().add(-14, 'd'), dayjs()] },
  { label: '过去 30 天', value: [dayjs().add(-30, 'd'), dayjs()] },
  { label: '过去 90 天', value: [dayjs().add(-90, 'd'), dayjs()] },
])

const categoryOptions = ref<string[]>([])
const tagOptions = ref<string[]>([])
const picFormatOptions = ref([
  { value: 'JPG' },
  { value: 'PNG' },
  { value: 'JPEG' },
  { value: 'GIF' },
])

// 获取标签和分类选项
const getTagCategoryOptions = async () => {
  const res = await listPictureTagCategoryUsingGet()
  if (res.data.code === 0 && res.data.data) {
    // 转换成下拉选项组件接受的格式
    tagOptions.value = (res.data.data.tagList ?? []).map((data: string) => {
      return {
        value: data,
        label: data,
      }
    })
    categoryOptions.value = (res.data.data.categoryList ?? []).map((data: string) => {
      return {
        value: data,
        label: data,
      }
    })
  } else {
    message.error('加载选项失败，' + res.data.message)
  }
}

// 清理
const doClear = () => {
  // 取消所有对象的值
  Object.keys(searchParams).forEach((key) => {
    searchParams[key] = undefined
  })
  dateRange.value = []
  props.onSearch?.(searchParams)
}


onMounted(() => {
  getTagCategoryOptions()
})
</script>

<template>
  <div class="picture-search-form">
    <div style="margin-bottom: 16px" />
    <a-form layout="inline" :model="searchParams" @finish="doSearch">
      <a-form-item label="关键词" name="searchText">
        <a-input
          v-model:value="searchParams.searchText"
          placeholder="从名称和简介搜索"
          allow-clear
        />
      </a-form-item>
      <a-form-item label="分类" name="category">
        <a-auto-complete
          style="min-width: 180px"
          v-model:value="searchParams.category"
          :options="categoryOptions"
          placeholder="请输入分类"
          allow-clear
        />
      </a-form-item>
      <a-form-item label="标签" name="tags">
        <a-select
          v-model:value="searchParams.tags"
          mode="tags"
          placeholder="请输入标签"
          :options="tagOptions"
          style="min-width: 180px"
          allow-clear
        />
      </a-form-item>
      <a-form-item>
        <a-space>
          <a-button type="primary" html-type="submit" style="width: 63.44px">搜索</a-button>
          <a-button html-type="reset" @click="doClear">重置</a-button>
        </a-space>
      </a-form-item>
    </a-form>
    <a-form-item style="width: 1000px">
      <a-collapse :defaultActiveKey="[]" bordered="false">
        <a-collapse-panel key="advanced" header="高级搜索">
          <!-- 日期范围 -->
          <a-form-item label="日期" name="dateRange">
            <a-range-picker
              style="width: 400px"
              show-time
              v-model:value="dateRange"
              :placeholder="['编辑开始日期', '编辑结束时间']"
              format="YYYY/MM/DD HH:mm:ss"
              :presets="rangePresets"
              @change="onRangeChange"
            />
          </a-form-item>

          <!-- 名称 -->
          <a-form-item label="名称" name="name">
            <a-input v-model:value="searchParams.name" placeholder="请输入名称" allow-clear />
          </a-form-item>

          <!-- 简介 -->
          <a-form-item label="简介" name="introduction">
            <a-input
              v-model:value="searchParams.introduction"
              placeholder="请输入简介"
              allow-clear
            />
          </a-form-item>

          <!-- 宽度 -->
          <a-form-item label="宽度" name="picWidth">
            <a-input-number v-model:value="searchParams.picWidth" />
          </a-form-item>

          <!-- 高度 -->
          <a-form-item label="高度" name="picHeight">
            <a-input-number v-model:value="searchParams.picHeight" />
          </a-form-item>

          <!-- 格式 -->
          <a-form-item label="格式" name="picFormat">
            <a-auto-complete
              v-model:value="searchParams.picFormat"
              :options="picFormatOptions"
              placeholder="请输入格式"
              style="min-width: 90px"
              allow-clear
            />
          </a-form-item>
        </a-collapse-panel>
      </a-collapse>
    </a-form-item>
  </div>
</template>

<style scoped>
.picture-search-form .ant-form-item {
  margin-bottom: 16px;
}
</style>
