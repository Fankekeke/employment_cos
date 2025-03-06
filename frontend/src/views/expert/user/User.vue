<template>
  <a-row :gutter="20">
    <a-col :span="8" style="text-align: center;padding-top: 50px">
      <i style="font-size: 30px;text-align: center;">学生信息</i>
      <a-card :loading="loading" :bordered="false">
        <a-form :form="form" layout="vertical">
          <a-row :gutter="20">
            <a-col :span="12">
              <a-form-item label='学生姓名' v-bind="formItemLayout">
                <a-input v-decorator="[
            'name',
            { rules: [{ required: true, message: '请输入学生姓名!' }] }
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="12">
              <a-form-item label='民族' v-bind="formItemLayout">
                <a-input v-decorator="[
            'nationality'
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label='性别' v-bind="formItemLayout">
                <a-input v-decorator="[
            'sex'
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label='政治面貌' v-bind="formItemLayout">
                <a-input v-decorator="[
            'politicalStatus'
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label='籍贯' v-bind="formItemLayout">
                <a-input v-decorator="[
            'nativePlace'
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label='工作单位' v-bind="formItemLayout">
                <a-input v-decorator="[
            'employer'
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label='职务' v-bind="formItemLayout">
                <a-input v-decorator="[
            'position'
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label='通讯地址' v-bind="formItemLayout">
                <a-input v-decorator="[
            'address'
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label='手机号' v-bind="formItemLayout">
                <a-input v-decorator="[
            'phone'
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label='专业方向一级' v-bind="formItemLayout">
                <a-input v-decorator="[
            'levelOne'
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label='专业方向二级' v-bind="formItemLayout">
                <a-input v-decorator="[
            'levelTwo'
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label='出生日期' v-bind="formItemLayout">
                <a-input v-decorator="[
            'birthDate'
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label='职称' v-bind="formItemLayout">
                <a-input v-decorator="[
            'jobTitle'
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label='特殊称谓' v-bind="formItemLayout">
                <a-input v-decorator="[
            'specialAppellation'
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label='邮箱' v-bind="formItemLayout">
                <a-input v-decorator="[
            'mail'
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item label='固定电话' v-bind="formItemLayout">
                <a-input v-decorator="[
            'fixedTelephone'
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="24">
              <a-form-item label='个人简介' v-bind="formItemLayout">
                <a-textarea :rows="6" v-decorator="[
            'profile',
             { rules: [{ required: true, message: '请输入名称!' }] }
            ]"/>
              </a-form-item>
            </a-col>
            <a-col :span="24">
              <a-form-item label='学生头像' v-bind="formItemLayout">
                <a-upload
                  name="avatar"
                  action="http://127.0.0.1:9527/file/fileUpload/"
                  list-type="picture-card"
                  :file-list="fileList"
                  @preview="handlePreview"
                  @change="picHandleChange"
                >
                  <div v-if="fileList.length < 1">
                    <a-icon type="plus" />
                    <div class="ant-upload-text">
                      Upload
                    </div>
                  </div>
                </a-upload>
                <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
                  <img alt="example" style="width: 100%" :src="previewImage" />
                </a-modal>
              </a-form-item>
            </a-col>
          </a-row>
        </a-form>
        <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
          修改
        </a-button>
      </a-card>
    </a-col>
    <a-col :span="16">
      <a-col :span="24" style="margin-top: 15px;background:#ECECEC; padding:30px;">
        <div v-if="postList.length === 0" style="font-size: 25px;text-align: center;margin-top: 85px;font-family: SimHei;">未找到符合岗位信息</div>
        <a-col :span="12" v-for="(item, index) in postList" :key="index">
          <div style="width: 100%;margin-bottom: 15px;text-align: left;box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 12px;">
            <a-card :bordered="false" hoverable>
              <a-card-meta style="margin-top: 5px;font-size: 13px">
                <template slot="title">
                  {{ item.address }} - {{ item.postName }}
                </template>
                <template slot="description">
                  {{ item.responsibility.slice(0, 25) }}...
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
    </a-col>
  </a-row>
</template>

<script>
import {mapState} from 'vuex'
import RentView from '../../manage/post/PostView.vue'

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
  name: 'User',
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    })
  },
  components: {RentView},
  data () {
    return {
      form: this.$form.createForm(this),
      formItemLayout,
      loading: false,
      fileList: [],
      postList: [],
      collectList: [],
      rentView: {
        visiable: false,
        data: null
      },
      previewVisible: false,
      previewImage: '',
      expertInfo: null
    }
  },
  mounted () {
    this.getExpertInfo(this.currentUser.userId)
    this.queryPostRecommend()
    this.queryCollect()
  },
  methods: {
    checkCollect (furnitureId) {
      if (this.collectList.length === 0) {
        return false
      }
      return this.collectList.some(item => item == furnitureId)
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
    queryPostRecommend () {
      this.$get(`/cos/post-info/queryPostRecommend`, {userId: this.currentUser.userId}).then((r) => {
        this.postList = r.data.data
      })
    },
    getExpertInfo (expertCode) {
      this.$get(`/cos/expert-info/detail/id/${expertCode}`).then((r) => {
        this.expertInfo = r.data.data
        console.log(this.expertInfo)
        this.setFormValues(this.expertInfo)
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
    setFormValues ({...expert}) {
      this.rowId = expert.id
      let fields = ['name', 'nationality', 'sex', 'politicalStatus', 'nativePlace', 'employer', 'position', 'address', 'phone', 'levelOne', 'levelTwo', 'birthDate', 'jobTitle', 'specialAppellation', 'mail', 'fixedTelephone', 'profile']
      let obj = {}
      Object.keys(expert).forEach((key) => {
        if (key === 'images') {
          this.fileList = []
          this.imagesInit(expert['images'])
        }
        if (fields.indexOf(key) !== -1) {
          this.form.getFieldDecorator(key)
          obj[key] = expert[key]
        }
      })
      this.form.setFieldsValue(obj)
    },
    handleSubmit () {
      // 获取图片List
      let images = []
      this.fileList.forEach(image => {
        if (image.response !== undefined) {
          images.push(image.response)
        } else {
          images.push(image.name)
        }
      })
      this.form.validateFields((err, values) => {
        values.id = this.rowId
        values.images = images.length > 0 ? images.join(',') : null
        if (!err) {
          this.loading = true
          this.$put('/cos/expert-info', {
            ...values
          }).then((r) => {
            this.$message.success('更新成功')
            this.loading = false
            this.getExpertInfo(this.currentUser.userId)
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
