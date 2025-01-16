<div id="ember7337" class="quiz-layout-head ember-view"><div class="step-wrapper">
  <div class="step-inner page-fragment">
    <div id="ember7338" class="html-content rich-text-viewer ember-view" data-processed=""><!----><span><p>Это задачка совмещает тренировку по материалу предыдущих двух модулей – необходимо разобраться и написать объект-ориентированный код и при этом коснуться свежих тем – исключений и логирования.<br></p><p>Дан набор классов, описывающих работу гипотетической почтовой системы.</p><p>Для начала рассмотрим код, описывающий все используемые сущности.<br></p><pre><code class="language-java hljs" data-highlighted="yes"><span class="hljs-comment">/*
Интерфейс: сущность, которую можно отправить по почте.
У такой сущности можно получить от кого и кому направляется письмо.
*/</span>
<span class="hljs-keyword">public</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">interface</span> <span class="hljs-title class_">Sendable</span> {
    String <span class="hljs-title function_">getFrom</span><span class="hljs-params">()</span>;
    String <span class="hljs-title function_">getTo</span><span class="hljs-params">()</span>;
}</code></pre><p>У Sendable есть два наследника, объединенные следующим абстрактным классом:</p><pre><code class="language-java hljs" data-highlighted="yes"><span class="hljs-comment">/*
Абстрактный класс,который позволяет абстрагировать логику хранения
источника и получателя письма в соответствующих полях класса.
*/</span>
<span class="hljs-keyword">public</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">abstract</span> <span class="hljs-keyword">class</span> <span class="hljs-title class_">AbstractSendable</span> <span class="hljs-keyword">implements</span> <span class="hljs-title class_">Sendable</span> {
    <span class="hljs-keyword">protected</span> <span class="hljs-keyword">final</span> String from;
    <span class="hljs-keyword">protected</span> <span class="hljs-keyword">final</span> String to;
    <span class="hljs-keyword">public</span> <span class="hljs-title function_">AbstractSendable</span><span class="hljs-params">(String from, String to)</span> {
        <span class="hljs-built_in">this</span>.from = from;
        <span class="hljs-built_in">this</span>.to = to;
    }
    <span class="hljs-meta">@Override</span>
    <span class="hljs-keyword">public</span> String <span class="hljs-title function_">getFrom</span><span class="hljs-params">()</span> {
        <span class="hljs-keyword">return</span> from;
    }
    <span class="hljs-meta">@Override</span>
    <span class="hljs-keyword">public</span> String <span class="hljs-title function_">getTo</span><span class="hljs-params">()</span> {
        <span class="hljs-keyword">return</span> to;
    }
    <span class="hljs-meta">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-type">boolean</span> <span class="hljs-title function_">equals</span><span class="hljs-params">(Object o)</span> {
        <span class="hljs-keyword">if</span> (<span class="hljs-built_in">this</span> == o) <span class="hljs-keyword">return</span> <span class="hljs-literal">true</span>;
        <span class="hljs-keyword">if</span> (o == <span class="hljs-literal">null</span> || getClass() != o.getClass()) <span class="hljs-keyword">return</span> <span class="hljs-literal">false</span>;
        <span class="hljs-type">AbstractSendable</span> <span class="hljs-variable">that</span> <span class="hljs-operator">=</span> (AbstractSendable) o;
        <span class="hljs-keyword">if</span> (!from.equals(that.from)) <span class="hljs-keyword">return</span> <span class="hljs-literal">false</span>;
        <span class="hljs-keyword">if</span> (!to.equals(that.to)) <span class="hljs-keyword">return</span> <span class="hljs-literal">false</span>;
        <span class="hljs-keyword">return</span> <span class="hljs-literal">true</span>;
    }

}</code></pre><p>Первый класс описывает обычное письмо, в котором находится только текстовое сообщение.</p><pre><code class="language-java hljs" data-highlighted="yes"><span class="hljs-comment">/*
Письмо, у которого есть текст, который можно получить с помощью метода `getMessage`
*/</span>
<span class="hljs-keyword">public</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">class</span> <span class="hljs-title class_">MailMessage</span> <span class="hljs-keyword">extends</span> <span class="hljs-title class_">AbstractSendable</span> {
    <span class="hljs-keyword">private</span> <span class="hljs-keyword">final</span> String message;
    <span class="hljs-keyword">public</span> <span class="hljs-title function_">MailMessage</span><span class="hljs-params">(String from, String to, String message)</span> {
        <span class="hljs-built_in">super</span>(from, to);
        <span class="hljs-built_in">this</span>.message = message;
    }
    <span class="hljs-keyword">public</span> String <span class="hljs-title function_">getMessage</span><span class="hljs-params">()</span> {
        <span class="hljs-keyword">return</span> message;
    }
    <span class="hljs-meta">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-type">boolean</span> <span class="hljs-title function_">equals</span><span class="hljs-params">(Object o)</span> {
        <span class="hljs-keyword">if</span> (<span class="hljs-built_in">this</span> == o) <span class="hljs-keyword">return</span> <span class="hljs-literal">true</span>;
        <span class="hljs-keyword">if</span> (o == <span class="hljs-literal">null</span> || getClass() != o.getClass()) <span class="hljs-keyword">return</span> <span class="hljs-literal">false</span>;
        <span class="hljs-keyword">if</span> (!<span class="hljs-built_in">super</span>.equals(o)) <span class="hljs-keyword">return</span> <span class="hljs-literal">false</span>;
        <span class="hljs-type">MailMessage</span> <span class="hljs-variable">that</span> <span class="hljs-operator">=</span> (MailMessage) o;
        <span class="hljs-keyword">if</span> (message != <span class="hljs-literal">null</span> ? !message.equals(that.message) : that.message != <span class="hljs-literal">null</span>) <span class="hljs-keyword">return</span> <span class="hljs-literal">false</span>;
        <span class="hljs-keyword">return</span> <span class="hljs-literal">true</span>;
    }

}</code></pre><br><p>Второй класс описывает почтовую посылку:</p><pre><code data-highlighted="yes" class="hljs language-typescript"><span class="hljs-comment">/*
Посылка, содержимое которой можно получить с помощью метода `getContent`
*/</span>
<span class="hljs-keyword">public</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">class</span> <span class="hljs-title class_">MailPackage</span> <span class="hljs-keyword">extends</span> <span class="hljs-title class_ inherited__">AbstractSendable</span> {
<span class="hljs-keyword">private</span> final <span class="hljs-title class_">Package</span> content;
    <span class="hljs-keyword">public</span> <span class="hljs-title class_">MailPackage</span>(<span class="hljs-title class_">String</span> <span class="hljs-keyword">from</span>, <span class="hljs-title class_">String</span> to, <span class="hljs-title class_">Package</span> content) {
        <span class="hljs-variable language_">super</span>(<span class="hljs-keyword">from</span>, to);
        <span class="hljs-variable language_">this</span>.<span class="hljs-property">content</span> = content;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-title class_">Package</span> <span class="hljs-title function_">getContent</span>(<span class="hljs-params"></span>) {
        <span class="hljs-keyword">return</span> content;
    }
    <span class="hljs-meta">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-built_in">boolean</span> <span class="hljs-title function_">equals</span>(<span class="hljs-params"><span class="hljs-title class_">Object</span> o</span>) {
        <span class="hljs-keyword">if</span> (<span class="hljs-variable language_">this</span> == o) <span class="hljs-keyword">return</span> <span class="hljs-literal">true</span>;
        <span class="hljs-keyword">if</span> (o == <span class="hljs-literal">null</span> || <span class="hljs-title function_">getClass</span>() != o.<span class="hljs-title function_">getClass</span>()) <span class="hljs-keyword">return</span> <span class="hljs-literal">false</span>;
        <span class="hljs-keyword">if</span> (!<span class="hljs-variable language_">super</span>.<span class="hljs-title function_">equals</span>(o)) <span class="hljs-keyword">return</span> <span class="hljs-literal">false</span>;
        <span class="hljs-title class_">MailPackage</span> that = (<span class="hljs-title class_">MailPackage</span>) o;
        <span class="hljs-keyword">if</span> (!content.<span class="hljs-title function_">equals</span>(that.<span class="hljs-property">content</span>)) <span class="hljs-keyword">return</span> <span class="hljs-literal">false</span>;
        <span class="hljs-keyword">return</span> <span class="hljs-literal">true</span>;
    }

}</code></pre><p>При этом сама посылка описывается следующим классом:</p><pre><code data-highlighted="yes" class="hljs language-kotlin"><span class="hljs-comment">/*
Класс, который задает посылку. У посылки есть текстовое описание содержимого и целочисленная ценность. /</span>
<span class="hljs-keyword">public</span> static <span class="hljs-keyword">class</span> <span class="hljs-title class_">Package</span> {
<span class="hljs-keyword">private</span> <span class="hljs-keyword">final</span> String content;
<span class="hljs-keyword">private</span> <span class="hljs-keyword">final</span> int price;
    <span class="hljs-keyword">public</span> Package(String content, int price) {
        <span class="hljs-keyword">this</span>.content = content;
        <span class="hljs-keyword">this</span>.price = price;
    }
    <span class="hljs-keyword">public</span> String getContent() {
        <span class="hljs-keyword">return</span> content;
    }
    <span class="hljs-keyword">public</span> int getPrice() {
        <span class="hljs-keyword">return</span> price;
    }
    <span class="hljs-meta">@Override</span>
    <span class="hljs-keyword">public</span> boolean equals(Object o) {
        <span class="hljs-keyword">if</span> (<span class="hljs-keyword">this</span> == o) <span class="hljs-keyword">return</span> <span class="hljs-literal">true</span>;
        <span class="hljs-keyword">if</span> (o == <span class="hljs-literal">null</span> || getClass() != o.getClass()) <span class="hljs-keyword">return</span> <span class="hljs-literal">false</span>;
        Package aPackage = (Package) o;
        <span class="hljs-keyword">if</span> (price != aPackage.price) <span class="hljs-keyword">return</span> <span class="hljs-literal">false</span>;
        <span class="hljs-keyword">if</span> (!content.equals(aPackage.content)) <span class="hljs-keyword">return</span> <span class="hljs-literal">false</span>;
        <span class="hljs-keyword">return</span> <span class="hljs-literal">true</span>;
    }
}</code></pre><p>Теперь рассмотрим классы, которые моделируют работу почтового сервиса:</p><pre><code data-highlighted="yes" class="hljs language-java"><span class="hljs-comment">/*
Интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.
*/</span>
<span class="hljs-keyword">public</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">interface</span> <span class="hljs-title class_">MailService</span> {
Sendable <span class="hljs-title function_">processMail</span><span class="hljs-params">(Sendable mail)</span>;
}

<span class="hljs-comment">/*
Класс, в котором скрыта логика настоящей почты
*/</span>
<span class="hljs-keyword">public</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">class</span> <span class="hljs-title class_">RealMailService</span> <span class="hljs-keyword">implements</span> <span class="hljs-title class_">MailService</span> {
    <span class="hljs-meta">@Override</span>
    <span class="hljs-keyword">public</span> Sendable <span class="hljs-title function_">processMail</span><span class="hljs-params">(Sendable mail)</span> {
        <span class="hljs-comment">// Здесь описан код настоящей системы отправки почты.</span>
        <span class="hljs-keyword">return</span> mail;
    }
}</code></pre><p>Вам необходимо описать набор классов, каждый из которых является MailService:</p><p>1) UntrustworthyMailWorker – класс, моделирующий ненадежного работника почты, который вместо того, чтобы передать почтовый объект непосредственно в сервис почты, последовательно передает этот объект набору третьих лиц, а затем, в конце концов, передает получившийся объект непосредственно экземпляру RealMailService. У UntrustworthyMailWorker должен быть конструктор от массива MailService ( результат вызова processMail первого элемента массива передается на вход processMail второго элемента, и т. д.) и метод getRealMailService, который возвращает ссылку на внутренний экземпляр RealMailService.<br></p><p>2) Spy – шпион, который логгирует о всей почтовой переписке, которая проходит через его руки. Объект конструируется от экземпляра Logger, с помощью которого шпион будет сообщать о всех действиях. Он следит только за объектами класса MailMessage и пишет в логгер следующие сообщения (в выражениях нужно заменить части в фигурных скобках на значения полей почты):<br>2.1) Если в качестве отправителя или получателя указан "Austin Powers", то нужно написать в лог сообщение с уровнем WARN: <i>Detected target mail correspondence: from {from} to {to} "{message}"</i><br>2.2) Иначе, необходимо написать в лог сообщение с уровнем I<i>NFO: Usual correspondence: from {from} to {to}</i></p><p>3) Thief – вор, который ворует самые ценные посылки и игнорирует все остальное. Вор принимает в конструкторе переменную int – минимальную стоимость посылки, которую он будет воровать. Также, в данном классе должен присутствовать метод getStolenValue, который возвращает суммарную стоимость всех посылок, которые он своровал. Воровство происходит следующим образом: вместо посылки, которая пришла вору, он отдает новую, такую же, только с нулевой ценностью и содержимым посылки "stones instead of {content}".</p><p>4) Inspector – Инспектор, который следит за запрещенными и украденными посылками и бьет тревогу в виде исключения, если была обнаружена подобная посылка. Если он заметил запрещенную посылку с одним из запрещенных содержимым ("weapons" и "banned substance"), то он бросает IllegalPackageException. Если он находит посылку, состоящую из камней (содержит слово "stones"), то тревога прозвучит в виде StolenPackageException. Оба исключения вы должны объявить самостоятельно в виде непроверяемых исключений.</p><p>Все классы должны быть определены как публичные и статические, так как в процессе проверки ваш код будет подставлен во внешний класс, который занимается тестированием и проверкой структуры. Для удобства во внешнем классе объявлено несколько удобных констант и импортировано все содержимое пакета java.util.logging. Для определения, посылкой или письмом является Sendable объект воспользуйтесь оператором instanceof.<br></p><pre><code data-highlighted="yes" class="hljs language-java"><span class="hljs-keyword">public</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">final</span> <span class="hljs-type">String</span> <span class="hljs-variable">AUSTIN_POWERS</span> <span class="hljs-operator">=</span> <span class="hljs-string">"Austin Powers"</span>;
<span class="hljs-keyword">public</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">final</span> <span class="hljs-type">String</span> <span class="hljs-variable">WEAPONS</span> <span class="hljs-operator">=</span> <span class="hljs-string">"weapons"</span>;
<span class="hljs-keyword">public</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">final</span> <span class="hljs-type">String</span> <span class="hljs-variable">BANNED_SUBSTANCE</span> <span class="hljs-operator">=</span> <span class="hljs-string">"banned substance"</span>;</code></pre><br></span></div>

</div>
</div>
</div>