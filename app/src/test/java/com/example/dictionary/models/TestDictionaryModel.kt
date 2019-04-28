package com.example.dictionary.models

class TestDictionaryModel {

    /*@Test
    fun getPagedList() {
        val dataSource: PositionalDataSource<InfoTranslation> =
            TestDataSource()
        val mainThread: MainThreadExecutor = mock(
            MainThreadExecutor::class.java
        )
        val config: PagedList.Config = mock(
            PagedList
                .Config
                .Builder()
                .setPageSize(10)
                .build()::class.java
        )
        val pagedList: PagedList<InfoTranslation> = mock(
            PagedList
                .Builder(dataSource, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .setNotifyExecutor(mainThread)
                .build()::class.java
        )
        `when`(presenter.getPagedList()).thenReturn(pagedList)
        assertEquals(presenter.getCallback(), pagedList)
        verify(presenter, times(2)).getPagedList()
    }

    inner class TestDataSource : PositionalDataSource<InfoTranslation>() {

        private val listTest: MutableList<InfoTranslation>

        init {
            listTest = ArrayList()
            listTest.add(mock(InfoTranslation::class.java))
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<InfoTranslation>) {
            callback.onResult(listTest, 0)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<InfoTranslation>) {}
    }*/
}
