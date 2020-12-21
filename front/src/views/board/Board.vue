<template>
    <div>
        <table style="margin: auto;border: 2px solid">
            <tr>
                <th v-for="th in boardTableTh" :key="th">
                    {{th}}
                </th>
            </tr>
            <tr v-for="board in boardModels" :key="board.boardSeq" @click="selectBoard(board)">
                <th v-for="th in Object.keys(boardTableTh)" :key="th">
                    {{board[th]}}
                </th>
            </tr>
        </table>
        <div>
            <div>
                <span v-show="prevBlock"
                    @click="getBoardByPage(prevBlockNum,pageVo.pageSize)">&laquo;</span>
                <span v-for="page in pageVo.pageArray()" :key="page" 
                    @click="page != pageVo.page ? getBoardByPage(page,pageVo.pageSize) : undefined">
                    {{page}}
                </span>
                <span v-show="nextBlock"
                    @click="getBoardByPage(nextBlockNum,pageVo.pageSize)">&raquo;</span>
            </div>
            <div>                
                <select ref="searchBy">
                    <option value="boardTitle" selected="selected">제목</option>
                    <option value="boardWriter">글쓴이</option>
                    <option value="boardContent">글내용</option>
                    <option value="boardReply">덧글</option>
                </select>
                <input type="text">
                <span style="border: 2px solid" @click="goSearch()">검색</span>
                <span style="border: 2px solid" @click="goWrite()">글쓰기</span>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    beforeMount(){
        axios.get('http://localhost:8000/board/count')
            .then(data=>{
                if(data.status === 200){
                    this.pageVo.boardCount = data.data
                    this.getBoardByPage(1,this.pageVo.pageSize)
                }                 
            })
    },
    data(){
        return {
            pageVo: {
                boardCount: 0,
                page: 1,
                pageSize: 7,
                block: () => parseInt((this.pageVo.page - 1)/ 5) + 1,
                pageCount: () => parseInt((this.pageVo.boardCount - 1)/ this.pageVo.pageSize) + 1,
                blockCount: () => parseInt((this.pageVo.pageCount() -1) / 5) + 1,
                pageArray: () => Array.from(
                    {length: this.pageVo.block() * 5 < this.pageVo.pageCount() 
                        ? 5 : this.pageVo.pageCount() - (this.pageVo.block() - 1) * 5},
                    (_,i)=> (this.pageVo.block() - 1) * 5 + i + 1)          
            },
            boardTableTh:{
                boardSeq:'번호',
                boardKind:'구분',
                boardTitle:'제목',
                boardWriter:'글쓴이',
                boardDate:'작성일',
                boardViewCount:'조회수',
                boardVote:'추천'
                },
            boardModels: {}
        }
    },
    computed:{
        prevBlock(){
            return this.pageVo.page > 5
        },
        prevBlockNum(){
            return (this.pageVo.block()-2)*5+1
        },
        nextBlock(){
            return this.pageVo.block() < this.pageVo.blockCount()
        },
        nextBlockNum(){
            return this.pageVo.block()*5+1
        }
    },
    methods:{
        getBoardByPage(page,pageSize){
            this.pageVo.page = page
            axios.get(`http://localhost:8000/board/page=${page}&pageSize=${pageSize}`)
                .then(data=>{
                    if(data.status === 200){
                        this.boardModels = data.data
                        console.log(
                            'page = '+ this.pageVo.page,
                            'block = '+ this.pageVo.block(),
                            'pageCount = '+ this.pageVo.pageCount(),
                            'blockCount = '+ this.pageVo.blockCount(),
                            'pageArray = '+ this.pageVo.pageArray()
                        )
                    }
                })
        },
        goWrite(){
            this.$router.push({name:'Write'})
        },
        goSearch(){
            console.log(this.$refs.searchBy.value)
            //this.$router.push({name:'Write'})
        },
        selectBoard(board){
            this.$router.push({name:'View',params: board})
        }
    }
}
</script>

<style>

</style>