<template>
    <div>
        <view-box v-model="boardModel"/>
        <div>
            <span @click="modify()">수정</span>
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
        modify(){
            axios.patch(
                `http://localhost:8000/board/patch`,
                this.boardModel
            )
                .then(data=>{
                    if(data.status === 200){
                        this.$router.push({name:'View',params: this.boardModel})
                    } else
                        console.log(data.status)
                })
        },
        cancle(){
            console.log('emit Test',this.boardModel)
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
        }
    }
}
</script>

<style>

</style>