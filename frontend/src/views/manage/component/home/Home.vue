<template>
  <div>
    <a-row style="margin-top: 15px">
      <a-col :span="24">
        <div style="background: #ECECEC; padding: 30px;" v-if="user.roleId == 74 || user.roleId == 75">
          <a-row :gutter="16">
            <a-col :span="6">
              <a-card hoverable>
                <a-row>
                  <a-col :span="24" style="font-size: 13px;margin-bottom: 8px;font-family: SimHei">本月预约数量</a-col>
                  <a-col :span="4"><a-icon type="arrow-up" style="font-size: 30px;margin-top: 3px"/></a-col>
                  <a-col :span="18" style="font-size: 28px;font-weight: 500;font-family: SimHei">
                    {{ titleData.monthOrderNum }}
                    <span style="font-size: 20px;margin-top: 3px">单</span>
                  </a-col>
                </a-row>
              </a-card>
            </a-col>
            <a-col :span="6">
              <a-card hoverable>
                <a-row>
                  <a-col :span="24" style="font-size: 13px;margin-bottom: 8px;font-family: SimHei">获取本月面试</a-col>
                  <a-col :span="4"><a-icon type="arrow-up" style="font-size: 30px;margin-top: 3px"/></a-col>
                  <a-col :span="18" style="font-size: 28px;font-weight: 500;font-family: SimHei">
                    {{ titleData.monthOrderTotal }}
                    <span style="font-size: 20px;margin-top: 3px">单</span>
                  </a-col>
                </a-row>
              </a-card>
            </a-col>
            <a-col :span="6">
              <a-card hoverable>
                <a-row>
                  <a-col :span="24" style="font-size: 13px;margin-bottom: 8px;font-family: SimHei">本年预约数量</a-col>
                  <a-col :span="4"><a-icon type="arrow-up" style="font-size: 30px;margin-top: 3px"/></a-col>
                  <a-col :span="18" style="font-size: 28px;font-weight: 500;font-family: SimHei">
                    {{ titleData.yearOrderNum }}
                    <span style="font-size: 20px;margin-top: 3px">单</span>
                  </a-col>
                </a-row>
              </a-card>
            </a-col>
            <a-col :span="6">
              <a-card hoverable>
                <a-row>
                  <a-col :span="24" style="font-size: 13px;margin-bottom: 8px;font-family: SimHei">本年面试</a-col>
                  <a-col :span="4"><a-icon type="arrow-up" style="font-size: 30px;margin-top: 3px"/></a-col>
                  <a-col :span="18" style="font-size: 28px;font-weight: 500;font-family: SimHei">
                    {{ titleData.yearOrderTotal }}
                    <span style="font-size: 20px;margin-top: 3px">单</span>
                  </a-col>
                </a-row>
              </a-card>
            </a-col>
          </a-row>
        </div>
      </a-col>
    </a-row>
    <a-row style="margin-top: 15px" v-if="user.roleId == 74 || user.roleId == 75" :gutter="25">
      <a-col :span="12">
        <div hoverable :bordered="false" style="width: 100%">
          <a-skeleton active v-if="loading" />
          <apexchart v-if="!loading" type="bar" height="300" :options="chartOptions1" :series="series1"></apexchart>
        </div>
      </a-col>
      <a-col :span="12">
        <div style="width: 100%">
          <a-skeleton active v-if="loading" />
          <apexchart  v-if="!loading" type="line" height="300" :options="chartOptions" :series="series"></apexchart>
        </div>
      </a-col>
    </a-row>
    <a-row style="margin-top: 15px">
      <a-col :span="24">
        <a-card hoverable :loading="loading" :bordered="false" title="公告信息" style="margin-top: 15px">
          <div style="padding: 0 22px">
            <a-list item-layout="vertical" :pagination="pagination" :data-source="bulletinList">
              <a-list-item slot="renderItem" key="item.title" slot-scope="item, index">
                <template slot="actions">
              <span key="message">
                <a-icon type="message" style="margin-right: 8px" />
                {{ item.date }}
              </span>
                </template>
                <a-list-item-meta :description="item.content" style="font-size: 14px">
                  <a slot="title">{{ item.title }}</a>
                </a-list-item-meta>
              </a-list-item>
            </a-list>
          </div>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script>
import {mapState} from 'vuex'
export default {
  name: 'Home',
  computed: {
    ...mapState({
      multipage: state => state.setting.multipage,
      user: state => state.account.user
    })
  },
  data () {
    return {
      pagination: {
        onChange: page => {
          console.log(page)
        },
        pageSize: 2
      },
      bulletinList: [],
      titleData: {
        monthOrderNum: 0,
        monthOrderTotal: 0,
        yearOrderNum: 0,
        yearOrderTotal: 0,
        monthPutNum: 0,
        monthPutPrice: 0,
        yearPutNum: 0,
        yearPutPrice: 0
      },
      loading: false,
      series: [{
        name: '预约',
        data: []
      }],
      chartOptions: {
        chart: {
          type: 'line',
          height: 300
        },
        xaxis: {
          categories: []
        },
        stroke: {
          curve: 'stepline'
        },
        dataLabels: {
          enabled: false
        },
        title: {
          text: '近十天内面试统计',
          align: 'left'
        },
        markers: {
          hover: {
            sizeOffset: 4
          }
        }
      },
      series1: [{
        name: '预约',
        data: []
      }],
      chartOptions1: {
        chart: {
          type: 'bar',
          height: 300
        },
        title: {
          text: '近十天会场预约',
          align: 'left'
        },
        plotOptions: {
          bar: {
            horizontal: false,
            columnWidth: '55%'
          }
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          show: true,
          width: 2,
          colors: ['transparent']
        },
        xaxis: {
          categories: []
        },
        yaxis: {
          title: {
            text: ''
          }
        },
        fill: {
          opacity: 1
        },
        tooltip: {
          y: {
            formatter: function (val) {
              return val + ' 单'
            }
          }
        }
      },
      series2: [{
        name: '预约',
        data: []
      }],
      chartOptions2: {
        chart: {
          type: 'line',
          height: 300
        },
        xaxis: {
          categories: []
        },
        stroke: {
          curve: 'stepline'
        },
        dataLabels: {
          enabled: false
        },
        title: {
          text: '近十天申请通过',
          align: 'left'
        },
        markers: {
          hover: {
            sizeOffset: 4
          }
        }
      },
      series3: [{
        name: '预约',
        data: []
      }],
      chartOptions3: {
        chart: {
          type: 'bar',
          height: 300
        },
        title: {
          text: '近十天内入库收益统计',
          align: 'left'
        },
        plotOptions: {
          bar: {
            horizontal: false,
            columnWidth: '55%'
          }
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          show: true,
          width: 2,
          colors: ['transparent']
        },
        xaxis: {
          categories: []
        },
        yaxis: {
          title: {
            text: ''
          }
        },
        fill: {
          opacity: 1
        },
        tooltip: {
          y: {
            formatter: function (val) {
              return val + ' 单'
            }
          }
        }
      }
    }
  },
  mounted () {
    this.loading = true
    this.selectHomeData()
    setTimeout(() => {
      this.loading = false
    }, 200)
  },
  methods: {
    selectHomeData () {
      if (this.user.roleId == 75) {
        this.$get('/cos/interview-info/homeData', {userId: this.user.userId}).then((r) => {
          this.bulletinList = r.data.bulletinInfoList
          // let titleData = { userNum: r.data.userNum, schoolNum: r.data.schoolNum, disciplineNum: r.data.disciplineNum, billNum: r.data.billNum }
          // this.$emit('setTitle', titleData)
          this.titleData.monthOrderNum = r.data.monthOrderNum
          this.titleData.monthOrderTotal = r.data.monthOrderTotal
          this.titleData.yearOrderNum = r.data.yearOrderNum
          this.titleData.yearOrderTotal = r.data.yearOrderTotal
          let values = []
          if (r.data.priceDayList !== null && r.data.priceDayList.length !== 0) {
            if (this.chartOptions.xaxis.categories.length === 0) {
              this.chartOptions.xaxis.categories = r.data.priceDayList.map(obj => { return obj.days })
            }
            let itemData = { name: '面试统计', data: r.data.priceDayList.map(obj => { return obj.count }) }
            values.push(itemData)
            this.series = values
          }
          this.series1[0].data = r.data.orderNumDayList.map(obj => { return obj.count })
          this.chartOptions1.xaxis.categories = r.data.orderNumDayList.map(obj => { return obj.days })
        })
      } else {
        this.$get('/cos/interview-info/homeData').then((r) => {
          this.bulletinList = r.data.bulletinInfoList
          // let titleData = { userNum: r.data.userNum, schoolNum: r.data.schoolNum, disciplineNum: r.data.disciplineNum, billNum: r.data.billNum }
          // this.$emit('setTitle', titleData)
          this.titleData.monthOrderNum = r.data.monthOrderNum
          this.titleData.monthOrderTotal = r.data.monthOrderTotal
          this.titleData.yearOrderNum = r.data.yearOrderNum
          this.titleData.yearOrderTotal = r.data.yearOrderTotal
          let values = []
          if (r.data.priceDayList !== null && r.data.priceDayList.length !== 0) {
            if (this.chartOptions.xaxis.categories.length === 0) {
              this.chartOptions.xaxis.categories = r.data.priceDayList.map(obj => { return obj.days })
            }
            let itemData = { name: '面试统计', data: r.data.priceDayList.map(obj => { return obj.count }) }
            values.push(itemData)
            this.series = values
          }
          this.series1[0].data = r.data.orderNumDayList.map(obj => { return obj.count })
          this.chartOptions1.xaxis.categories = r.data.orderNumDayList.map(obj => { return obj.days })
        })
      }
    }
  }
}
</script>

<style scoped>

</style>
