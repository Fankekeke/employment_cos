<template>
  <a-modal v-model="show" title="预约审核" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose">
        取消
      </a-button>
      <a-button key="pass" type="danger" :loading="loading" @click="handleSubmit(2)">
        驳回
      </a-button>
      <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit(1)">
        通过
      </a-button>
    </template>
    <div style="font-size: 13px;font-family: SimHei" v-if="expert !== null">
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">学生信息</span></a-col>
        <a-col :span="8"><b>学生姓名：</b>
          {{ expert.name }}
        </a-col>
        <a-col :span="8"><b>民族：</b>
          {{ expert.nationality }}
        </a-col>
        <a-col :span="8"><b>性别：</b>
          {{ expert.sex }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>政治面貌：</b>
          {{ expert.politicalStatus }}
        </a-col>
        <a-col :span="8"><b>籍贯：</b>
          {{ expert.nativePlace }}
        </a-col>
        <a-col :span="8"><b>工作单位：</b>
          {{ expert.employer }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>职务：</b>
          {{ expert.position }}
        </a-col>
        <a-col :span="16"><b>通讯地址：</b>
          {{ expert.address }}
        </a-col>
        <br/>
        <br/>
        <a-col :span="8"><b>手机号：</b>
          {{ expert.phone }}
        </a-col>
        <a-col :span="8"><b>专业方向一级：</b>
          {{ expert.levelOne }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <br/>
        <a-col :span="8"><b>出生日期：</b>
          {{ expert.birthDate }}
        </a-col>
        <a-col :span="16"><b>专业方向二级：</b>
          {{ expert.levelTwo }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>职称：</b>
          {{ expert.jobTitle }}
        </a-col>
        <a-col :span="8"><b>特殊称谓：</b>
          {{ expert.specialAppellation }}
        </a-col>
        <a-col :span="8"><b>邮箱：</b>
          {{ expert.mail }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>固定电话：</b>
          {{ expert.fixedTelephone }}
        </a-col>
        <a-col :span="8"><b>创建时间：</b>
          {{ expert.createDate }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="24"><b>个人简介：</b>
          {{ expert.profile }}
        </a-col>
      </a-row>
      <br/>
    </div>
    <br/>
    <div style="font-size: 13px;font-family: SimHei" v-if="resume !== null">
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">简历信息</span></a-col>
        <a-col :span="8"><b>简历名称：</b>
          {{ resume.name }}
        </a-col>
        <a-col :span="8"><b>简历附件：</b>
          <a :href="'http://127.0.0.1:9527/imagesWeb/' + resume.fileUrl">{{ resume.fileUrl }}</a>
        </a-col>
      </a-row>
    </div>
    <a-form :form="form" layout="vertical" style="margin-top: 10px">
      <a-row :gutter="20" style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">备注</span></a-col>
        <a-col :span="24">
          <a-form-item label='' v-bind="formItemLayout">
            <a-textarea :rows="6" v-decorator="[
            'remark'
            ]"/>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-modal>
</template>

<script>
import {mapState} from 'vuex'
function getBase64 (file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}
const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
export default {
  name: 'reserveEdit',
  props: {
    reserveEditVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.reserveEditVisiable
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      rowId: null,
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      fileList: [],
      previewVisible: false,
      previewImage: '',
      venue: null,
      resume: null,
      reserve: null,
      expert: null
    }
  },
  methods: {
    queryReserveDetail (id) {
      this.$get(`/cos/reserve-info/queryReserveDetail/${id}`).then((r) => {
        this.venue = r.data.venue
        this.resume = r.data.resume
        this.reserve = r.data.reserve
        this.expert = r.data.expert
      })
    },
    handleCancel () {
      this.previewVisible = false
    },
    async handlePreview (file) {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj)
      }
      this.previewImage = file.url || file.preview
      this.previewVisible = true
    },
    picHandleChange ({ fileList }) {
      this.fileList = fileList
    },
    imagesInit (images) {
      if (images !== null && images !== '') {
        let imageList = []
        images.split(',').forEach((image, index) => {
          imageList.push({uid: index, name: image, status: 'done', url: 'http://127.0.0.1:9527/imagesWeb/' + image})
        })
        this.fileList = imageList
      }
    },
    setFormValues ({...reserve}) {
      this.rowId = reserve.id
      this.queryReserveDetail(this.rowId)
      let fields = ['title', 'content', 'publisher', 'rackUp']
      let obj = {}
      Object.keys(reserve).forEach((key) => {
        if (key === 'images') {
          this.fileList = []
          this.imagesInit(reserve['images'])
        }
        if (key === 'rackUp') {
          reserve[key] = reserve[key].toString()
        }
        if (fields.indexOf(key) !== -1) {
          this.form.getFieldDecorator(key)
          obj[key] = reserve[key]
        }
      })
      this.form.setFieldsValue(obj)
    },
    reset () {
      this.loading = false
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    handleSubmit (status) {
      this.form.validateFields((err, values) => {
        values.id = this.rowId
        values.status = status
        if (!err) {
          this.loading = true
          this.$put('/cos/reserve-info', {
            ...values
          }).then((r) => {
            this.reset()
            this.$emit('success')
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
