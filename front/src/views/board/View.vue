<template>
    <div>
        <view-box>
            <template #title>
                <h3>{{boardModel.boardTitle}}</h3>
            </template>
            <template #writer>
                <h3>{{boardModel.boardWriter}}</h3>
            </template>
            <template #type>
                <h3>{{boardType}}</h3>
            </template>
            <template #content>
                <h3>{{boardModel.boardContent}}</h3>
            </template>
        </view-box>
        <div>
            <span @click="goModify()">수정</span>
            <span @click="cancle()">취소</span>
        </div>
        <div title="reply" style="text-align: initial">
            <span v-for="i in replyListSort" :key="i">
                <span v-for="j in i.replyDepth" :key="j">---></span>
                {{
                    i.userId +':'
                    + i.replyContent 
                    + ' replyNo = '+i.replyNo
                    +' inNo = '+i.inReplyNo}}
                <button @click="replyWrite(i)">대댓글</button>
                <br>
            </span>
            <div>
                <input v-model="replyModel.replyContent" type="text">
                <button @click="replyWrite">답글쓰기</button>
            </div>
        </div>
    </div>
</template>

<script>
import ViewBox from '@/components/ViewBox.vue'
import {mapGetters} from 'vuex'
import axios from 'axios'

export default {
    components:{ViewBox},
     beforeMount(){
        if(this.$route.params.boardTitle == undefined)
            this.getBoardBySeq(this.$route.params.boardSeq)
        this.getReplyBySeq(this.$route.params.boardSeq)
    },
    data(){
        return{
            boardModel: this.$route.params,
            replyModel: {
                replyNo: '',
                replyContent: '',
                replyDepth: 0,
                boardSeq: this.boardModel ? this.boardModel.boardSeq : this.$route.params.boardSeq,
                userId: '아무개',
                inReplyNo: 0
            },
            replyList: [],
        }
    },
    computed:{
         ...mapGetters(
            ['getBoardTypes']
        ),
        boardType:{
            get(){
            const temp = this.getBoardTypes[Math.log2(this.boardModel.boardType)]
            return temp ? temp.text : '모름'
            }
        },
        replyListSort(){
            let reReplyList = []
            let result = this.replyList.filter(a => {
                const judge = a.replyDepth == 0
                if(!judge) reReplyList.push(a)
                return judge
            })
            reReplyList.sort((a,b)=> 
                a.inReplyNo == b.inReplyNo
                    ? b.replyNo - a.replyNo
                    : a.inReplyNo - b.inReplyNo
                )
                .forEach(a=>{
                result.splice(result.findIndex(b=>{
                    //console.log('re = '+a.replyNo+'     rein = '+a.inReplyNo+'     list = '+b.replyNo)
                    return b.replyNo == a.inReplyNo})+1,0,a)
            })            
            return result
        }
    },
    methods:{
        goModify(){
            this.$router.push({name:'Modify',params: this.boardModel})
        },
        cancle(){
            this.$router.push({name:'Board'})
        },
        getBoardBySeq(seq){
            axios.get(`http://localhost:8000/board/seq=${seq}`)
            .then(data=>{
                if(data.status === 200){
                    this.boardModel = data.data
                    console.log('받은 보드 데이터',this.boardModel)
                }                 
            })
            .catch(e => console.log(e))
        },
        getReplyBySeq(seq){
            axios.get(`http://localhost:8000/comment/seq=${seq}`)
            .then(data=>{
                if(data.status === 200){
                    this.replyList = data.data
                    console.log('받은 리플 데이터',this.replyList)
                }                 
            })
            .catch(e => console.log(e))
        },
        writeComment(reply){
            axios.post(`http://localhost:8000/comment`,reply)
            .then(data=>{
                if(data.status === 200){
                    console.log('등록한 덧글 replyNo',data.data)
                    reply.replyNo = data.data
                    this.replyList.push(reply)
                }                 
            })
            .catch(e => console.log(e))
        },
        replyWrite(inReply){
            //답글썻을시 따로 새로고침을 하지않는데 답글에v-model때문에 값이 항상 같이 변해서 함수형으로 처리
            const replyF = a => {
                return {
                    replyContent: a.replyContent,
                    replyDepth : inReply.replyNo ? inReply.replyDepth + 1 : 0,
                    boardSeq: a.boardSeq,
                    userId: a.userId,
                    inReplyNo: inReply.replyNo ? inReply.replyNo : 0
                }
            }
            const reply = replyF(this.replyModel)
            this.writeComment(reply)
        },
    }
}
</script>

<style>

</style>