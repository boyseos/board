<template>
    <div>
        <view-box :boardProps="boardModel" />
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
        // axios.get(`http://localhost:8000/board/seq=${this.seq}`)
        //     .then(data=>{
        //         if(data.status === 200){
        //             this.boardModel = data.data
        //             console.log('보낸 데이터',this.boardModel)
        //         }                 
        //     })
        //     .catch(e => console.log(e))
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
        }
    }
}
</script>

<style>

</style>