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
                <h3>{{$store.getters.getBoardTypes[Math.log2(boardModel.boardType)].text}}</h3>
            </template>
            <template #content>
                <h3>{{boardModel.boardContent}}</h3>
            </template>
        </view-box>
        <div>
            <span @click="goModify()">수정</span>
            <span @click="cancle()">취소</span>
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
    },
    data(){
        return{
            boardModel: this.$route.params
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
    }
}
</script>

<style>

</style>