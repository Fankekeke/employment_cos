<template>
  <a-row :gutter="20" style="width: 100%;margin-top: 30px">
    <a-col>
      <a-form layout="horizontal">
        <a-row :gutter="15">
          <div>
            <a-col :md="6" :sm="24">
              <a-form-item
                label="企业名称"
                :labelCol="{span: 4}"
                :wrapperCol="{span: 18, offset: 2}">
                <a-input v-model="queryParams.enterpriseName"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item
                label="工作地点"
                :labelCol="{span: 4}"
                :wrapperCol="{span: 18, offset: 2}">
                <a-input v-model="queryParams.address"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item
                label="岗位名称"
                :labelCol="{span: 4}"
                :wrapperCol="{span: 18, offset: 2}">
                <a-input v-model="queryParams.postName"/>
              </a-form-item>
            </a-col>
          </div>
          <span style="float: right; margin-top: 3px;">
            <a-button type="primary" @click="search">查询</a-button>
          </span>
        </a-row>
      </a-form>
    </a-col>
    <a-col :span="24" style="margin-top: 15px;background:#ECECEC; padding:30px;">
      <div v-if="rentList.length === 0" style="font-size: 25px;text-align: center;margin-top: 85px;font-family: SimHei;">未找到符合岗位信息</div>
      <a-col :span="6" v-for="(item, index) in rentList" :key="index">
        <div style="width: 100%;margin-bottom: 15px;text-align: left;box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 12px;">
          <a-card :bordered="false" hoverable>
            <a-card-meta style="margin-top: 5px;font-size: 13px">
              <template slot="title">
                {{ item.address }} - {{ item.postName }}
              </template>
              <template slot="description">
                {{ item.responsibility.slice(0, 40) }}...
              </template>
            </a-card-meta>
            <div style="font-size: 13px;font-family: SimHei;margin-top: 15px">
              <p>{{ item.welfare }}</p> |
              <span  style="margin-left: 2px">{{ item.industryName }}</span> |
              <span style="margin-left: 2px">{{ item.workHour }}</span> |
              <span style="color: #f5222d; font-size: 13px;float: right">{{ item.salary }}</span>
            </div>
            <div style="font-size: 13px;font-family: SimHei;margin-top: 15px">
              <a-avatar shape="square" icon="user" :src="'http://127.0.0.1:9527/imagesWeb/' + item.images"/>
              <b style="margin-left: 5px">{{ item.enterpriseName }}</b>
              <a @click="chat(item)">【联系】</a>
            </div>
            <template slot="actions" class="ant-card-actions">
              <div @click="sendInter(item)"><a-icon key="folder-add" type="folder-add"/> 投递</div>
              <div @click="rentDetail(item)"><a-icon key="contacts" type="contacts"/> 详情</div>
              <div v-if="checkCollect(item.id)" @click="sendNotCollect(item)"><a-icon type="heart" style="cursor: pointer;color: red" /> 取消</div>
              <div v-else @click="sendCollect(item)"><a-icon type="heart" style="cursor: pointer;" /> 收藏</div>
            </template>
          </a-card>
        </div>
      </a-col>
    </a-col>
    <rent-view :pluralismShow="rentView.visiable" :pluralismData="rentView.data" @close="rentView.visiable = false"></rent-view>
  </a-row>
</template>

<script>
import RentView from '../../manage/post/PostView.vue'
import {mapState} from 'vuex'
export default {
  name: 'House',
  components: {RentView},
  data () {
    return {
      rentList: [],
      collectList: [],
      rentView: {
        visiable: false,
        data: null
      },
      queryParams: {}
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    })
  },
  mounted () {
    this.selectRentList()
    this.queryCollect()
  },
  methods: {
    checkCollect (furnitureId) {
      if (this.collectList.length === 0) {
        return false
      }
      return this.collectList.some(item => item == furnitureId)
    },
    queryCollect () {
      this.$get(`/cos/collect-info/queryCollectByUser/${this.currentUser.userId}`).then((r) => {
        this.collectList = r.data.data
      })
    },
    chat (item) {
      this.$post(`/cos/chat-info/saveFirst`, {
        expertId: this.currentUser.userId,
        enterpriseCode: item.enterpriseCode,
        type: 1,
        content: '你好'
      }).then((r) => {
        this.$router.push('/expert/chat')
      })
    },
    search () {
      this.selectRentList({
        ...this.queryParams
      })
    },
    rentDetail (row) {
      this.rentView.visiable = true
      this.rentView.data = row
    },
    sendNotCollect (row) {
      this.$put('/cos/collect-info/sendNotCollect', {
        expertId: this.currentUser.userId,
        baseId: row.id
      }).then((r) => {
        this.$message.success('取消收藏成功')
        this.queryCollect()
      })
    },
    sendCollect (row) {
      this.$post('/cos/collect-info', {
        expertId: this.currentUser.userId,
        baseId: row.id,
        type: 3,
        enterpriseId: row.enterpriseId
      }).then((r) => {
        this.$message.success('收藏成功')
        this.queryCollect()
      })
    },
    sendInter (row) {
      this.$post('/cos/interview-info', {
        expertId: this.currentUser.userId,
        baseId: row.id,
        type: 2,
        enterpriseId: row.enterpriseId
      }).then((r) => {
        this.$message.success('投递成功')
      })
    },
    selectRentList (params = {}) {
      params.delFlag = 1
      this.$get('/cos/post-info/page', {...params}).then((r) => {
        this.rentList = r.data.data.records
      })
    }
  }
}
</script>

<style scoped>
>>> .ant-card-meta-title {
  font-size: 16px;
  font-family: SimHei;
}
>>> .ant-card-meta-description {
  font-size: 14px;
  font-family: SimHei;
}
>>> .ant-divider-with-text-left {
  margin: 0;
}

>>> .ant-card-head-title {
  font-size: 14px;
  font-family: SimHei;
}
>>> .ant-card-extra {
  font-size: 13px;
  font-family: SimHei;
}
.ant-carousel >>> .slick-slide {
  text-align: center;
  height: 250px;
  line-height: 250px;
  overflow: hidden;
}
</style>
