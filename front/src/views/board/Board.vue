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
                    {{th != 'boardKind' ? board[th] : boardKinds[Math.log2(board.boardKind)].text}}
                </th>
            </tr>
        </table>
        <div>
            <div>
                <span v-show="prevBlock"
                    @click="getBoard(prevBlockNum)">&laquo;</span>
                <span v-for="page in pageVo.pageArray()" :key="page" 
                    @click="page != pageVo.page ? getBoard(page) : undefined">
                    {{page}}
                </span>
                <span v-show="nextBlock"
                    @click="getBoard(nextBlockNum)">&raquo;</span>
            </div>
            <div>
                <label v-for="(box) in boardKinds" :key="box">
                    <input :ref="'checked'+box.value" @click="check" :value="box.value" type="checkbox" checked>{{box.text}}
                </label>
                <select v-model="getBoardRequest.searchType">
                    <option value="boardTitle" selected="selected">제목</option>
                    <option value="boardWriter">글쓴이</option>
                    <option value="boardContent">글내용</option>
                    <option value="boardReply">덧글</option>
                </select>
                <input ref="searchWord" type="text">
                <span style="border: 2px solid" @click="getBoard(0)">검색</span>
                <span style="border: 2px solid" @click="goWrite()">글쓰기</span>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    beforeMount(){
        this.getBoardRequest.boardKind = Math.pow(2,this.boardKinds.length) - 1
        this.getBoardCount()
        this.getBoard(1)
    },
    data(){
        return {
            win: window,
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
            getBoardRequest:{
                startRow : 0,
                rowLimit : 0,
                boardKind : 0,
                searchType : 'boardTitle',
                searchWord : undefined
            },
            boardKinds:[
                {value:1, text:'자유'},
                {value:2, text:'Q&A'},
                {value:4, text:'질문'},
                {value:8, text:'Test'}
            ],
            boardTableTh:{
                boardSeq:'번호',
                boardKind:'구분',
                boardTitle:'제목',
                boardWriter:'글쓴이',
                boardDate:'작성일',
                boardViewCount:'조회수',
                boardVote:'추천'
                },
            boardModels: {},
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
        getBoardCount(){
            axios.get(`http://localhost:8000/board/count/param=${JSON.stringify(this.getBoardRequest)}`)
            .then(data=>{
                if(data.status === 200){
                    this.pageVo.boardCount = data.data
                }                 
            })
        },
        async getBoard(page){
            if(page===0){
                page = 1
                this.getBoardRequest.searchWord = this.$refs.searchWord.value
                await this.getBoardCount()
            }
            this.pageVo.page = page
            this.getBoardRequest.startRow = (page-1) * this.pageVo.pageSize
            this.getBoardRequest.rowLimit = this.pageVo.pageSize
            console.log('getBoardRequest',this.getBoardRequest)
            axios.get(`http://localhost:8000/board/param=${JSON.stringify(this.getBoardRequest)}`)
                .then(data=>{
                    if(data.status === 200){
                        this.boardModels = data.data
                    }
                })
        },
        goWrite(){
            this.$router.push({name:'Write'})
        },
        selectBoard(board){
            this.$router.push({name:'View',params: board})
        },
        check(){
            const temp = this.boardKinds.map(
                a => this.$refs['checked'+a.value].checked ? a.value : 0)
                .reduce((a,b) => a+b)
            this.getBoardRequest.boardKind = temp ? temp : Math.pow(2,this.boardKinds.length) - 1
                
        }        
    }
}
</script>

<style>

</style>