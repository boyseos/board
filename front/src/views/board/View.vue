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
        <div title="reply" style="">
            <span v-for="i in replyListSort" :key="i">
                {{(i.replyDepth != 0 ? '대댓글' : '')
                    +i.userId +':'
                    + i.replyContent 
                    + ' replyNo = '
                    +i.replyNo+' inNo = '
                    +i.inReplyNo}}
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
                inReplyNo: ''
            },
            replyList: []
        }
    },
    computed:{
        boardType(){
            let temp = this.$store.getters.getBoardTypes[Math.log2(this.boardModel.boardType)]
            return temp ? temp.text : undefined
        },
        replyListSort(){
            let reReplyList = []
            //let reReplyIndex = []
            let result = this.replyList.filter(a => {
                const judge = a.replyDepth == 0
                if(!judge) reReplyList.push(a)
                console.log(judge)
                return judge
            })
            let i = 0
            result.forEach((a,aindex)=>{
                console.log('결과배열 변화 = ',result)
                reReplyList.forEach((b,bindex)=>{
                    if(a.replyNo == b.inReplyNo){
                        result.splice(aindex+1,0,b)
                        reReplyList.splice(bindex,1)
                    }
                    i++
                })
            })
            // reReplyList.sort((a,b)=> a.replyNo-b.replyNo).forEach(c=>{
            //     result.splice(result.findIndex(d=>{
            //         i++
            //         return d.replyNo == c.inReplyNo})+1,0,c)
            // })
            console.log('총반복 = ',i)
            return result;
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
                    console.log('보낸 데이터',this.boardModel)
                }                 
            })
            .catch(e => console.log(e))
        },
        getReplyBySeq(seq){
            axios.get(`http://localhost:8000/comment/seq=${seq}`)
            .then(data=>{
                if(data.status === 200){
                    this.replyList = data.data
                    console.log('보낸 리플 데이터',this.replyList)
                }                 
            })
            .catch(e => console.log(e))
        },
        writeComment(reply){
            axios.post(`http://localhost:8000/comment`,reply)
            .then(data=>{
                if(data.status === 200){
                    console.log('덧글등록',data.data)
                }                 
            })
            .catch(e => console.log(e))
        },
        replyWrite(inReply){
            const replyF = a => {
                return {
                    replyContent: a.replyContent,
                    replyDepth : inReply ? inReply.replyDepth + 1 : 0,
                    boardSeq: a.boardSeq,
                    userId: a.userId,
                    inReplyNo: inReply ? inReply.replyNo : 0
                }
            }
            const reply = replyF(this.replyModel)
            this.writeComment(reply)
            this.replyList.push(reply)
        },
    }
}
</script>

<style>

</style>