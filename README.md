eBanking Application
--
A Java-based mobile banking application simulation implementing core banking functionalities with object-oriented design patterns.


Features
--
Core Banking Operations
--
- User Management: Create accounts with email, name, and address
- Multi-Currency Support: USD, EUR, GBP, JPY, CAD accounts
- Money Transfers: Send money between friends with automatic fee calculation
- Currency Exchange: Real-time exchange with configurable rates
- Stock Trading: Buy stocks with USD, track portfolio performance

Smart Recommendations
--
- Stock Analysis: Automated recommendations using Simple Moving Averages (SMA) crossover strategy
- 5-day vs 10-day SMA: Identifies profitable buying opportunities

Premium Features
--
- Premium Accounts: $100 USD upgrade fee
- No Exchange Fees: Waived 1% commission on large currency exchanges
- Stock Discounts: 5% discount on recommended stocks


Commands
--
User Management
--
- CREATE USER <email> <firstname> <lastname> <address>
- ADD FRIEND <emailUser> <emailFriend>
- ADD ACCOUNT <email> <currency>

Transactions
--
- ADD MONEY <email> <currency> <amount>
- EXCHANGE MONEY <email> <sourceCurrency> <destinationCurrency> <amount>
- TRANSFER MONEY <email> <friendEmail> <currency> <amount>

Trading & Analysis
--
- BUY STOCKS <email> <company> <noOfStocks>
- RECOMMEND STOCKS

Information
--
- LIST USER <email>
- LIST PORTFOLIO <email>

Premium (Bonus)
--
- BUY PREMIUM <email>


Input Files
--
- exchangeRates.csv: Currency exchange rates matrix
- stockValues.csv: 10-day historical stock prices for SMA calculations
- commands.txt: Command sequences to execute


Technical Implementation
--
- Design Patterns: Implements minimum 4 OOP design patterns
- JSON Output: Structured data format for listings and recommendations
- CSV Processing: Uses Java CSV parsing libraries (e.g., OpenCSV)
- Error Handling: Comprehensive validation and error messages
- User: Email-identified accounts with personal data and friend networks
- Account: Currency-specific balances with transaction capabilities
- Stock: Company shares with 10-day price history for analysis
