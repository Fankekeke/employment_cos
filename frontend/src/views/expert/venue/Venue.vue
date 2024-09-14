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
                label="会场名称"
                :labelCol="{span: 4}"
                :wrapperCol="{span: 18, offset: 2}">
                <a-input v-model="queryParams.name"/>
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
      <div v-if="rentList.length === 0" style="font-size: 25px;text-align: center;margin-top: 85px;font-family: SimHei;">未找到会场信息</div>
      <a-col :span="6" v-for="(item, index) in rentList" :key="index">
        <div style="width: 100%;margin-bottom: 15px;text-align: left;box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 12px;">
          <a-card :bordered="false" hoverable>
            <a-card-meta style="margin-top: 5px">
              <template slot="title">
                {{ item.name }} - {{ item.code }}
              </template>
              <template slot="description">
                {{ item.address }}...
              </template>
            </a-card-meta>
            <div style="font-size: 14px;font-family: SimHei;margin-top: 15px">
              <p>招聘时间：{{ item.startDate }} ~ {{ item.endDate }}</p>
            </div>
            <div style="font-size: 15px;font-family: SimHei;margin-top: 15px">
              <a-avatar shape="square" icon="user" :src="'http://127.0.0.1:9527/imagesWeb/' + item.logo"/>
              <b style="margin-left: 5px">{{ item.enterpriseName }}</b>
            </div>
            <template slot="actions" class="ant-card-actions">
              <div @click="sendInter(item)"><a-icon key="folder-add" type="folder-add"/> 预约</div>
              <div @click="venueViewOpen(item)"><a-icon key="contacts" type="contacts"/> 详情</div>
            </template>
          </a-card>
        </div>
      </a-col>
    </a-col>
    <venue-view
      @close="handlevenueViewClose"
      :venueShow="venueView.visiable"
      :venueData="venueView.data">
    </venue-view>
  </a-row>
</template>

<script>
import RentView from '../../manage/post/PostView.vue'
import {mapState} from 'vuex'
import venueView from './VenueView.vue'
export default {
  name: 'House',
  components: {RentView, venueView},
  data () {
    return {
      rentList: [],
      rentView: {
        visiable: false,
        data: null
      },
      venueView: {
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
  },
  methods: {
    venueViewOpen (row) {
      this.venueView.data = row
      this.venueView.visiable = true
    },
    handlevenueViewClose () {
      this.venueView.visiable = false
    },
    search () {
      this.selectRentList({
        ...this.queryParams
      })
    },
    sendInter (row) {
      this.$post('/cos/reserve-info', {
        studentId: this.currentUser.userId,
        venueId: row.id
      }).then((r) => {
        this.$message.success('预约提交成功,请等待审核')
      })
    },
    selectRentList (params = {}) {
      params.delFlag = 1
      params.size = 999
      this.$get('/cos/venue-info/page', {...params}).then((r) => {
        this.rentList = r.data.data.records
        console.log(this.rentList)
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
  font-size: 15px;
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
